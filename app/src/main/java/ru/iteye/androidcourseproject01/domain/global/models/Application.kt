package ru.iteye.androidcourseproject01.domain.global.models

import android.content.Context
import android.support.multidex.MultiDexApplication

class Application: MultiDexApplication() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
    }


}