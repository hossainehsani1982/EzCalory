package com.hossain_ehs.tracker_presentation.tracker_search_food

import com.hossain_ehs.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val trackableFood: TrackableFood,
    val isExpanded : Boolean = false,
    val amount : String = ""
)
