package ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose

import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface AuthChooseView: BaseView {
    fun startAuthEmailActivity()
    fun startRegEmailActivity()
}