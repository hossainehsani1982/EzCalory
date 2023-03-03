package com.hossain_ehs.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.model.ActivityLevel
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel(){
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val _activityLevelChannel = Channel<UiEvents>()
    val activityLevelChannel = _activityLevelChannel.receiveAsFlow()

    fun onLevelSelected(activityLevel: ActivityLevel){
         selectedActivityLevel = activityLevel
    }

    fun onNextClicked(){
        viewModelScope.launch {
            _activityLevelChannel.send(UiEvents.NavigateUp)
            preferences.saveActivityLevel(selectedActivityLevel)
        }
    }

}