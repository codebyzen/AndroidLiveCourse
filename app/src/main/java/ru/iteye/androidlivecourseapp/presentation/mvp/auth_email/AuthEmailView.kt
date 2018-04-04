package ru.iteye.androidlivecourseapp.presentation.mvp.auth_email

import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

interface AuthEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWeakPassword(password: EditText)

    fun onSuccessAuth()

    fun showFirebaseErrorMessage(errorType: ErrorsTypes)

}