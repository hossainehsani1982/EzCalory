package com.hossain_ehs.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.domain.use_cases.FilterDigitsUseCases
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitsUseCases: FilterDigitsUseCases,
    private val validateNutrientsUseCase: ValidateNutrientsUseCase
) : ViewModel() {
    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _nutrientGoalChannel = Channel<UiEvents>()
    val nutrientGoalChannel = _nutrientGoalChannel.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRationEntered -> {
                state = state.copy(
                    carbRatio = filterDigitsUseCases.filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnProteinRatioEntered -> {
                state = state.copy(
                    proteinRatio = filterDigitsUseCases.filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnFatRatioEntered -> {
                state = state.copy(
                    fatRatio = filterDigitsUseCases.filterOutDigits(event.ratio)
                )
            }
            NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrientsUseCase(
                    carbRatioText = state.carbRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )
                when(result){
                    is ValidateNutrientsUseCase.Result.Success -> {
                        preferences.saveCarbRatio(result.carbRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _nutrientGoalChannel.send(UiEvents.NavigateUp)
                        }
                    }
                    is ValidateNutrientsUseCase.Result.Error -> {
                        viewModelScope.launch {
                            _nutrientGoalChannel.send(UiEvents.ShowSnackBar(result.message))
                        }
                    }
                }
            }
        }
    }

}