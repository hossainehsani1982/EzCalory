package com.hossain_ehs.tracker_presentation.tracker_search_food

import com.hossain_ehs.tracker_domain.model.MealType
import com.hossain_ehs.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvents {
    data class OnQueryChanged(val query: String) : SearchEvents()
    object OnSearch : SearchEvents()
    data class OnToggleTrackableFood(val trackableFood: TrackableFood) : SearchEvents()
    data class OnAmountForTrackableFoodChanged(
        val trackableFood: TrackableFood,
        val amount: String
    ) : SearchEvents()

    data class OnTrackFoodClicked(
        val trackableFood: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvents()

    data class OnSearchFocusChanged(val isFocused : Boolean) : SearchEvents()
}