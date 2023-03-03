package com.hossain_ehs.onboarding_presentation.weight

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
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitsUseCases: FilterDigitsUseCases
) : ViewModel(){
    var weight by mutableStateOf("80.0")
        private set

    private val _weightChannel = Channel<UiEvents>()
    val weightChannel = _weightChannel.receiveAsFlow()

    fun onWeightEntered(weight : String){
        if (weight.length <= 5){
            this.weight = filterDigitsUseCases.filterOutDigitsAndDot(weight)
        }
    }
    fun onNextClicked(){
        viewModelScope.launch {
            val weightToFloat = weight.toFloatOrNull() ?: kotlin.run {
                UiEvents.ShowSnackBar(
                    UiText
                    .ResourceString(
                        com.hossain_ehs.core.R.string.error_weight_cant_be_empty
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightToFloat)
            _weightChannel.send(UiEvents.NavigateUp)
        }
    }

}