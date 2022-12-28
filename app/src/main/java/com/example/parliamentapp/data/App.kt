package com.example.parliamentapp.data

import android.app.Application
import android.content.Context
/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: Class for content setting
 */
////Application class for setting the context
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Context
    }
}