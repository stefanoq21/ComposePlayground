package com.stefanoq21.composeplayground.domain.di

import com.stefanoq21.composeplayground.ui.navigation.NavigationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val myModules = module {

    viewModelOf(::NavigationViewModel)

}