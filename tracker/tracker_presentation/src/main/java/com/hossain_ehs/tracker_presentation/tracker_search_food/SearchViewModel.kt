package com.hossain_ehs.tracker_presentation.tracker_search_food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.use_cases.FilterDigitsUseCases
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.tracker_domain.use_case.TrackerUseCases
import com.hossain_ehs.core.R
import com.hossain_ehs.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterDigitsUseCases: FilterDigitsUseCases,
    private val saveState : SavedStateHandle

) : ViewModel() {


    var state by mutableStateOf(SearchState())
        private set
    val mealName  = saveState.get<String>("meal_name") ?: ""
    val day = saveState.get<Int>("day") ?: 1
    val month = saveState.get<Int>("month") ?: 1
    val year = saveState.get<Int>("year") ?: 1

    private val _searchUiEvens = Channel<UiEvents>()
    val searchUiEvens = _searchUiEvens.receiveAsFlow()

    fun onEvent(events: SearchEvents) {
        when (events) {
            is SearchEvents.OnQueryChanged -> {
                state = state.copy(
                    searchQuery = events.query
                )
            }
            is SearchEvents.OnAmountForTrackableFoodChanged -> {
                state = state.copy(
                    trackableFoodUiState = state.trackableFoodUiState.map {
                        if (it.trackableFood == events.trackableFood) {
                            it.copy(
                                amount = filterDigitsUseCases.filterOutDigits(events.amount)
                            )
                        } else it
                    }
                )
            }
            SearchEvents.OnSearch -> {
                executeSearch()
            }
            is SearchEvents.OnSearchFocusChanged -> {
                state = state.copy(
                    isHintVisible = !events.isFocused && state.searchQuery.isBlank()
                )
            }
            is SearchEvents.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFoodUiState = state.trackableFoodUiState.map {
                        if (it.trackableFood == events.trackableFood) {
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        } else it
                    }
                )
            }
            is SearchEvents.OnTrackFoodClicked -> {
                trackFood(events)
            }
        }

    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFoodUiState = emptyList()
            )
            trackerUseCases
                .searchFoodUseCase(
                    query = state.searchQuery,
                    page = 1,
                    pageSize = 40
                ).onSuccess { trackableFoods->
                    state = state.copy(
                        trackableFoodUiState = trackableFoods.map {
                            TrackableFoodUiState(
                                trackableFood = it
                            )
                        },
                        isSearching = false,
                        searchQuery = ""
                    )
                }
                .onFailure {
                    _searchUiEvens.send(UiEvents
                        .ShowSnackBar(UiText.ResourceString(R.string.error_something_went_wrong))
                    )
                }
        }
    }

    private fun trackFood(events: SearchEvents.OnTrackFoodClicked) {
        viewModelScope.launch {
            val uiState = state.trackableFoodUiState.find {
                it.trackableFood == events.trackableFood
            }
            trackerUseCases.trackFoodUseCase(
                food = uiState?.trackableFood ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = events.mealType,
                date = events.date,
            )
            _searchUiEvens.send(UiEvents.NavigateUp)
        }
    }

}