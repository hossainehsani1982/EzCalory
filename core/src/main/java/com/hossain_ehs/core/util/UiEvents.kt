package com.hossain_ehs.core.util

sealed class UiEvents {
    object NavigateUp: UiEvents()
    data class ShowSnackBar(val message: UiText) : UiEvents()
}