package ru.iteye.androidcourseproject01.presentation.authchoose

import ru.iteye.androidcourseproject01.presentation.view.authchoose.AuthChooseView


class AuthChoosePresenter {

    private var view : AuthChooseView? = null

    fun getView() : AuthChooseView? {
        return view
    }

    fun setView(view : AuthChooseView?){
        this.view = view
    }

    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

}