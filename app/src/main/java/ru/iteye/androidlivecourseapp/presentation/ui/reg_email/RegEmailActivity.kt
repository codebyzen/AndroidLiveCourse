package ru.iteye.androidlivecourseapp.presentation.ui.reg_email

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.reg_email.RegEmailPresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.reg_email.RegEmailView
import ru.iteye.androidlivecourseapp.presentation.ui.friends_list.FriendsListActivity
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.utils.ValidateUtils


class RegEmailActivity : BaseActivity(), RegEmailView {

    private val regEmailPresenter = RegEmailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reg_screen_email)

        regEmailPresenter.setView(this)
        Log.d("***", "RegEmailActivity created!")
    }

    override fun onDestroy() {
        super.onDestroy()
        regEmailPresenter.destroyObserver()
    }

    fun onBtnClickReg(view: View){
        Log.d("***","RegEmailActivity -> onBtnClickReg")

        if (validateFormEmailAuth()) {
            val email = this.findViewById<EditText>(R.id.input_email).text.toString()
            val password = this.findViewById<EditText>(R.id.input_password).text.toString()
            regEmailPresenter.registration(email, password)
        }
    }

    private fun validateFormEmailAuth(): Boolean {

        val email = this.findViewById<EditText>(R.id.input_email)
        val password = this.findViewById<EditText>(R.id.input_password)
        val checkBox = this.findViewById<CheckBox>(R.id.checkbox_agree_terms)

        if (!checkBox.isChecked) {
            onTermsIsNotAccepted(checkBox)
            return false
        }

        if (!ValidateUtils.isValidEmail(email.text)) {
            onWrongEmail(email)
            return false
        }

        if (!ValidateUtils.isValidPassword(password.toString())) {
            onWrongPassword(password)
            return false
        }

        return true
    }


    override fun onTermsIsNotAccepted(checkBox: CheckBox) {
        checkBox.error = getString(R.string.terms_is_not_accepted)
    }

    override fun onWrongEmail(email: EditText) {
        email.error = getString(R.string.wrong_email)
    }

    override fun onWrongPassword(password: EditText) {
        password.error = getString(R.string.wrong_password)
    }

    override fun onUserRegistered(){
        showMessage(getString(R.string.user_registered), true)
        val intent = Intent(this, FriendsListActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun onUserAlreadyExist() {
        showError(getString(R.string.ERROR_EMAIL_ALREADY_IN_USE))
    }


}


