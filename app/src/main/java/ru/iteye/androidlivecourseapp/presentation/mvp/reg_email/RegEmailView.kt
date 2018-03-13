package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email
import android.widget.CheckBox
import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface RegEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWrongPassword(password: EditText)
    fun onUserRegistered()
    fun onSuccessReg()
    fun onFailedReg()
    fun onFailedFirebaseReg()
    fun onTermsIsNotAccepted(checkBox: CheckBox)
}