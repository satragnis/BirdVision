package com.example.smartvision.core

import android.content.Context
import androidx.multidex.MultiDexApplication

class MainApplication : MultiDexApplication(){
    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any
        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = applicationContext()
    }
}