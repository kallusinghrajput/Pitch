package com.pitch.ui.base

import android.app.Application
import com.pitch.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        timberInitialization()
    }


    //this is use for timber initialization only for debug time
    private fun timberInitialization() {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}