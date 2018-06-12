package ru.iteye.androidlivecourseapp.presentation.splash_screen

import ru.iteye.androidlivecourseapp.presentation.global.BaseView

interface SplashView: BaseView {
    fun startAuthChooseActivity()
    fun startFriendsListActivity()
    fun googlePlayServiceOutdate()
    fun onUserDisabled()
    fun onNoInternet()
}