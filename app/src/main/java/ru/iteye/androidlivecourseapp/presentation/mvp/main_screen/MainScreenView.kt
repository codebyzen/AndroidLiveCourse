package ru.iteye.androidlivecourseapp.presentation.mvp.main_screen

import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface MainScreenView: BaseView {
    fun showMessage(msg: String)
}