package ru.iteye.androidcourseproject01.presentation.AuthChoose

class AuthChoosePresenter(view: AuthChooseActivity) {

    private val view: AuthChooseActivity = view

    fun startAuthEmailActivity() {
        this.view.startAuthEmailActivity()
    }

}