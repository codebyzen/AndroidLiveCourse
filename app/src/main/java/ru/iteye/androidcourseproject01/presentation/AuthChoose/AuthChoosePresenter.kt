package ru.iteye.androidcourseproject01.presentation.authchoose

import ru.iteye.androidcourseproject01.presentation.global.BasePresenter
import ru.iteye.androidcourseproject01.presentation.view.authchoose.AuthChooseView


class AuthChoosePresenter: BasePresenter<AuthChooseView>() {

    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

}