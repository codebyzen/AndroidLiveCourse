package ru.iteye.androidcourseproject01.presentation.mvp.splash_screen

interface SplashView {
    fun startAuthChooseActivity()
    fun showCustomAlertDialog(customMessage: String)
    fun startFriendsListActivity()
}