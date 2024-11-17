package com.stefanoq21.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.stefanoq21.composeplayground.ui.navigation.MainNavHost
import com.stefanoq21.composeplayground.ui.navigation.NavigationEvent
import com.stefanoq21.composeplayground.ui.navigation.NavigationViewModel
import com.stefanoq21.composeplayground.ui.theme.ComposePlaygroundTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navigationViewModel = koinViewModel<NavigationViewModel>()

            val navController = rememberNavController()

            navigationViewModel.onEvent(
                NavigationEvent.OnSetContent(
                    activityNavController = navController,
                ) { onBackPressedDispatcher.onBackPressed() })

            ComposePlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavHost(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
