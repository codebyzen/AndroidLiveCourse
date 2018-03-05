package ru.iteye.androidcourseproject01.presentation.mvp.splash_screen

import ru.iteye.androidcourseproject01.presentation.view.base.BaseView

interface SplashView: BaseView {
    fun startAuthChooseActivity()
    fun startFriendsListActivity()
}