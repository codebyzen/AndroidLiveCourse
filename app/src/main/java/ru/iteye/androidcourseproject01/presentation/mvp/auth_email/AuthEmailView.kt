package ru.iteye.androidcourseproject01.presentation.mvp.auth_email

import android.widget.EditText
import ru.iteye.androidcourseproject01.presentation.view.base.BaseView

interface AuthEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onUserRegistered()
    fun onSuccessAuth()
    fun onFailedAuth()
}