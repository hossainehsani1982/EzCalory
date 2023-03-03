package com.hossain_ehs.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.domain.use_cases.FilterDigitsUseCases
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitsUseCases: FilterDigitsUseCases
) : ViewModel(){

    var height by mutableStateOf("170")
        private set
    private val _heightChannel = Channel<UiEvents>()
    val heightChannel = _heightChannel.receiveAsFlow()

    fun onHeightEntered(height : String){
        if (height.length <= 3){
            this.height = filterDigitsUseCases.filterOutDigits(height)
        }
    }
    fun onNextClicked(){
        viewModelScope.launch {
            val heightToInt = height.toIntOrNull() ?: kotlin.run {
                UiEvents.ShowSnackBar(
                    UiText
                    .ResourceString(
                        com.hossain_ehs.core.R.string.error_height_cant_be_empty
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightToInt)
            _heightChannel.send(UiEvents.NavigateUp)
        }
    }

}