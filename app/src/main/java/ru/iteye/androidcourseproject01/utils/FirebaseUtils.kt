package ru.iteye.androidcourseproject01.utils

import ru.iteye.androidcourseproject01.domain.global.models.Application

object FirebaseUtils {
    fun getGoogleFirebaseVersion(): Int? = Application.appContext.packageManager?.getPackageInfo("com.google.android.gms", 0)?.versionCode
}