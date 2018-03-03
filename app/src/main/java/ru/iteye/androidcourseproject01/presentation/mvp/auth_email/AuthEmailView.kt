package ru.iteye.androidcourseproject01.presentation.mvp.auth_email

import android.widget.EditText

interface AuthEmailView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onUserRegistered()
    fun onSuccessAuth()
    fun onFailedAuth()
}