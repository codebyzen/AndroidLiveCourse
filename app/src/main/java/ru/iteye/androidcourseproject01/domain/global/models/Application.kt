package ru.iteye.androidcourseproject01.domain.global.models

import android.content.Context
import android.support.multidex.MultiDexApplication


open class Application: MultiDexApplication() {

    val appContext: Context = applicationContext

}