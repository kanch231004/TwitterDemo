package com.example.twitterdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TwitterApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}