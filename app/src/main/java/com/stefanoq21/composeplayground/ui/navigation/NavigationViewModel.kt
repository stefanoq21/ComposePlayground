package com.stefanoq21.composeplayground.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class NavigationViewModel : ViewModel() {
    private lateinit var onBackPressed: () -> Unit

    lateinit var activityNavController: NavHostController
        private set


    fun onEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.OnSetContent -> {
                activityNavController = event.activityNavController
                onBackPressed = event.onBackPressed
            }

            is NavigationEvent.OnBack -> onBackPressed()


            is NavigationEvent.OnNavigateToScreen -> {
                activityNavController.navigate(event.screen)
            }

        }
    }


}