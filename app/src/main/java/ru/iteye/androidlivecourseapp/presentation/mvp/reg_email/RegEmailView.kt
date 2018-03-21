package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email
import android.widget.CheckBox
import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface RegEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onUserRegistered()
    fun onFailedReg()
    fun onTermsIsNotAccepted(checkBox: CheckBox)
}