package ru.iteye.androidlivecourseapp.presentation.auth_email

import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.global.BaseView
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

interface AuthEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWeakPassword(password: EditText)

    fun onSuccessAuth()

    fun showFirebaseErrorMessage(errorType: ErrorsTypes)

    fun startRegEmailActivity()

}