package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email
import android.widget.CheckBox
import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface RegEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onPasswordsNotEqual(password_retype: EditText)
    fun onUserRegistered()
    fun onUserAlreadyExist()
    fun onTermsIsNotAccepted(checkBox: CheckBox)
}