package com.hossain_ehs.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.util.UiEvents
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.hossain_ehs.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverViewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : ViewModel() {
    init {
        preferences.saveShouldShowOnboarding(false)
    }

    var state by mutableStateOf(TrackerOverViewState())
        private set

    private var getFoodsForDateJob: Job? = null

    private val _trackerOverViewChannel = Channel<UiEvents>()
    val trackerOverViewChannel = _trackerOverViewChannel.receiveAsFlow()

    fun onEvent(event: TrackerOverViewEvent) {
        when (event) {
            is TrackerOverViewEvent.OnAddFoodClicked -> {
                viewModelScope.launch {
                    _trackerOverViewChannel.send(
                        UiEvents.NavigateToSearchFood(
                            mealType = event.meal.mealType.name,
                            day = state.date.dayOfMonth,
                            month = state.date.monthValue,
                            year = state.date.year
                        )
                    )
                }
            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClicked -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoodsAfter()
                }
            }
            TrackerOverViewEvent.OnNextDayClicked -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoodsAfter()
            }
            TrackerOverViewEvent.OnPreviousDayClicked -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoodsAfter()
            }
            is TrackerOverViewEvent.OnToggleMealClicked -> {
                state = state.copy(
                    meal = state.meal.map {
                        if (it.name == event.meal.name) {
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        } else it
                    }
                )
            }
        }
    }

    private fun refreshFoodsAfter() {
        getFoodsForDateJob?.cancel()

        getFoodsForDateJob = trackerUseCases
            .getFoodsForDate(state.date)
            .onEach { foods ->
                //on each list received from data base
                val nutrientResult = trackerUseCases.calculateMealNutrients(foods)
                state = state.copy(
                    totalCarbs = nutrientResult.totalCarbs,
                    totalProtein = nutrientResult.totalProteins,
                    totalFat = nutrientResult.totalFat,
                    totalCalories = nutrientResult.totalCalories,
                    carbsGoal = nutrientResult.carbsGoal,
                    proteinGoal = nutrientResult.proteinGoal,
                    fatGoal = nutrientResult.fatGoal,
                    caloriesGoal = nutrientResult.caloriesGoal,
                    trackedFoods = foods,
                    meal = state.meal.map {
                        val nutrientForMeal =
                            nutrientResult.mealNutrient[it.mealType]//get by key
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                        it.copy(
                            carbs = nutrientForMeal.carbs,
                            protein = nutrientForMeal.protein,
                            fat = nutrientForMeal.fat,
                            calories = nutrientForMeal.calories
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}