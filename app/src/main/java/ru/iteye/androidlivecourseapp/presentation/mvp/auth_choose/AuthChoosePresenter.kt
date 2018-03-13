package ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose

import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter


class AuthChoosePresenter: BasePresenter<AuthChooseView>() {

    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

    fun startRegEmailActivity() {
        getView()?.startRegEmailActivity()
    }

}