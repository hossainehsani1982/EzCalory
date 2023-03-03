package com.hossain_ehs.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.domain.use_cases.FilterOutDigits
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.core.util.UiText
import com.hossain_ehs.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
):ViewModel() {
    var age by mutableStateOf("20")
         private set
    private val _ageChannel = Channel<UiEvents>()
    val ageChannel = _ageChannel.receiveAsFlow()

    fun onAgeEntered(age : String){
        if (age.length <= 3){
            this.age = filterOutDigits(age)
        }
    }
    fun onNextClicked(){
        viewModelScope.launch {
            var ageToInt = age.toIntOrNull() ?: kotlin.run {
                UiEvents.ShowSnackBar(UiText
                    .ResourceString(
                        com.hossain_ehs.core.R.string.error_age_cant_be_empty
                    )
                )
                return@launch
            }
            preferences.saveAge(ageToInt)
            _ageChannel.send(UiEvents.NavigateUp)
        }
    }

}
