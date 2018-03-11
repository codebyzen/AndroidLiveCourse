package ru.iteye.androidlivecourseapp.utils

import com.google.android.gms.common.GoogleApiAvailability
import ru.iteye.androidlivecourseapp.domain.global.models.Application

object GooglePlayUtils {
    fun getGooglePlayCurrentVersion(): Int? = Application.appContext.packageManager?.getPackageInfo("com.google.android.gms", 0)?.versionCode
    fun getGooglePlayNewestVersion(): Int? = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE
}