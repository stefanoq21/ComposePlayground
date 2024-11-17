package com.stefanoq21.composeplayground.ui.navigation

import androidx.navigation.NavHostController

sealed interface NavigationEvent {
    data class OnSetContent(
        val activityNavController: NavHostController,
        val onBackPressed: () -> Unit
    ) : NavigationEvent

    data object OnBack : NavigationEvent
    data class OnNavigateToScreen(val screen: Screen) : NavigationEvent
}