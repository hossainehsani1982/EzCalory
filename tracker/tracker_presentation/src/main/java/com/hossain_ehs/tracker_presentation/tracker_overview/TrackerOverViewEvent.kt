package com.hossain_ehs.tracker_presentation.tracker_overview

import com.hossain_ehs.tracker_domain.model.TrackedFood

sealed class TrackerOverViewEvent {
    object OnNextDayClicked : TrackerOverViewEvent()
    object OnPreviousDayClicked : TrackerOverViewEvent()
    data class OnToggleMealClicked(val meal : Meal) : TrackerOverViewEvent()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood) : TrackerOverViewEvent()
    data class OnAddFoodClicked(val meal :  Meal) : TrackerOverViewEvent()

}