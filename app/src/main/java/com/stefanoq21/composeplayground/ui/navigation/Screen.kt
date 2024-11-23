package com.stefanoq21.composeplayground.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Screen1 : Screen

    @Serializable
    data object Screen2 : Screen

    @Serializable
    data object GraphicsLayerScreen : Screen

    @Serializable
    data object HiddenAreaScreen : Screen


    companion object {
        fun getSubScreens(): List<Screen> = listOf(Screen1, Screen2, GraphicsLayerScreen, HiddenAreaScreen)
    }
}