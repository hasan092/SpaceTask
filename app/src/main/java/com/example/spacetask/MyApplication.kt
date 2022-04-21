package com.example.spacetask

import androidx.multidex.MultiDexApplication
import com.example.spacetask.data.NetworkService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        NetworkService.init()
    }
}