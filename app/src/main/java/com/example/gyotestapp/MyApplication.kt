package com.example.gyotestapp

import android.app.Application
import android.util.Log
import com.example.gyotestapp.model.DI.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        Log.d("Starting Koin", "onCreate")
        super.onCreate()
        startKoin {
            Log.d("Starting Koin", "onStartKoin")
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
            Log.d("Starting Koin", "Executed")
        }
    }
}