package com.hossain_ehs.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent{
    data class OnCarbRationEntered(val ratio : String) : NutrientGoalEvent()
    data class OnProteinRatioEntered(val ratio: String) : NutrientGoalEvent()
    data class OnFatRatioEntered(val ratio: String) : NutrientGoalEvent()
    object OnNextClicked : NutrientGoalEvent()
}
