package com.hossain_ehs.tracker_presentation.tracker_search_food

data class SearchState(
    val searchQuery: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFoodUiState: List<TrackableFoodUiState> = emptyList(),
    val mealName : String = "",
    val day : Int = 1,
    val month : Int = 1,
    val year : Int = 1
)