package com.hossain_ehs.tracker_presentation.tracker_overview

import androidx.lifecycle.ViewModel
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TrackerOverViewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : ViewModel(){
        init {
            preferences.saveShouldShowOnboarding(false)
        }
    private val _trackerOverViewChannel = Channel<UiEvents>()
    val trackerOverViewChannel = _trackerOverViewChannel.receiveAsFlow()

    fun onEvent(event: TrackerOverViewEvent){
        when (event){
            is TrackerOverViewEvent.OnAddFoodClicked -> {

            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClicked -> {
                TODO()
            }
            TrackerOverViewEvent.OnNextDayClicked -> {
                TODO()
            }
            TrackerOverViewEvent.OnPreviousDayClicked ->  {
                TODO()
            }
            is TrackerOverViewEvent.OnToggleMealClicked -> {
                TODO()
            }
        }
    }
}