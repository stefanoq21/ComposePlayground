package com.stefanoq21.composeplayground

import android.app.Application
import com.stefanoq21.composeplayground.domain.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(myModules)
        }
    }
}