package com.hossain_ehs.core_ui

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationModule) =
        when (navigationFlow) {
        NavigationModule.OnBoardingModule ->
            navController.navigate(NavigationMainDirections.actionGlobalOnboardingNav())
        is NavigationModule.TrackerModule ->
            navController.navigate(NavigationMainDirections.actionGlobalTrackerNav())
    }
}