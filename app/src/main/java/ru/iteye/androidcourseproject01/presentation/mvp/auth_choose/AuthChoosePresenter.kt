package ru.iteye.androidcourseproject01.presentation.mvp.auth_choose

import ru.iteye.androidcourseproject01.presentation.mvp.global.BasePresenter


class AuthChoosePresenter: BasePresenter<AuthChooseView>() {

    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

}