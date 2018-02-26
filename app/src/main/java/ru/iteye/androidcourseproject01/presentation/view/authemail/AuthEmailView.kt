package ru.iteye.androidcourseproject01.presentation.view.authemail

import android.widget.EditText

/**
 * Created by ugputu on 25/02/2018.
 */
interface AuthEmailView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onUserRegistered()
    fun onSuccessAuth()
    fun onFailedAuth()
}