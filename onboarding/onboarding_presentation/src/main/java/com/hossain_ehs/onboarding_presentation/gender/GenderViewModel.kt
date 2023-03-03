package com.hossain_ehs.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.core.domain.model.Gender
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
   private val preferences: Preferences
):ViewModel() {
 private val _genderChannel = Channel<GenderEvents>()

    val genderChannel = _genderChannel.receiveAsFlow()

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set


     fun onGenderClicked(gender: Gender){
         selectedGender = gender
     }
     fun onNextClicked(){
         navigate()
    }
    private fun navigate(){
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            _genderChannel.send(GenderEvents.Navigate)
        }
    }

}

sealed class GenderEvents(){
    object Navigate : GenderEvents()
}