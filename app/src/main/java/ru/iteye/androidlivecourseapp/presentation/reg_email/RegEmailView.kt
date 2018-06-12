package ru.iteye.androidlivecourseapp.presentation.reg_email
import android.widget.CheckBox
import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.global.BaseView

interface RegEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onPasswordsNotEqual(password_retype: EditText)
    fun onUserRegistered()
    fun onUserAlreadyExist()
    fun onTermsIsNotAccepted(checkBox: CheckBox)
}