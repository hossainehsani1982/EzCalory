package com.hossain_ehs.core.util

sealed class UiEvents {
    object NavigateUp: UiEvents()

    data class NavigateToSearchFood(
        val mealType : String,
        val day : Int,
        val month : Int,
        val year : Int
    ) : UiEvents()
    data class ShowSnackBar(val message: UiText) : UiEvents()
}