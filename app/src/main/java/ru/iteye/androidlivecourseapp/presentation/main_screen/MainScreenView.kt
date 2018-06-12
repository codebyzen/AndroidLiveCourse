package ru.iteye.androidlivecourseapp.presentation.main_screen

import ru.iteye.androidlivecourseapp.presentation.global.BaseView

interface MainScreenView: BaseView {
    fun showMessage(msg: String)
}