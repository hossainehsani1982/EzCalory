package com.hossain_ehs.core_ui

sealed class NavigationModule {
    object OnBoardingModule : NavigationModule()
    object TrackerModule : NavigationModule()
}