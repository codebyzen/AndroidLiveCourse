package ru.iteye.androidcourseproject01.presentation.view.splash

import android.content.Context

interface SplashView {
    fun startAuthChooseActivity()
    fun showMessage(msg: String, isToast: Boolean)
    fun showCustomAlertDialog(customMessage: String)
    fun getActivityContext(): Context
    fun startFriendsListActivity()
}