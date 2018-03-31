package ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen

import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface SplashView: BaseView {
    fun startAuthChooseActivity()
    fun startFriendsListActivity()
    fun googlePlayServiceOutdate()
    fun onNoInternet()
}