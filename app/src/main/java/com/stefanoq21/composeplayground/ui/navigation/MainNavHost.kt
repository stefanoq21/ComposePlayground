package com.stefanoq21.composeplayground.ui.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stefanoq21.composeplayground.ui.screen.expressiveDesign.ExpressiveDesignScreen
import com.stefanoq21.composeplayground.ui.screen.graphicsLayer.GraphicsLayerScreen
import com.stefanoq21.composeplayground.ui.screen.hiddenAreaScreen.HiddenAreaScreen
import com.stefanoq21.composeplayground.ui.screen.home.HomeInitScreen
import com.stefanoq21.composeplayground.ui.screen.shaderBrush.ShaderBrushScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = koinViewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity),
) {

    NavHost(
        navController = navigationViewModel.activityNavController,
        startDestination = Screen.Home,
        modifier = modifier
    ) {
        composable<Screen.Home> {
            HomeInitScreen()
        }

        composable<Screen.Screen1> {
            Text("Screen1")
        }

        composable<Screen.Screen2> {
            Text("Screen2")
        }

        composable<Screen.GraphicsLayerScreen> {
            GraphicsLayerScreen()
        }
        composable<Screen.HiddenAreaScreen> {
            HiddenAreaScreen()
        }
        composable<Screen.ShaderBrushScreen> {
            ShaderBrushScreen()
        }
        composable<Screen.ExpressiveDesignScreen> {
            ExpressiveDesignScreen()
        }


    }
}

