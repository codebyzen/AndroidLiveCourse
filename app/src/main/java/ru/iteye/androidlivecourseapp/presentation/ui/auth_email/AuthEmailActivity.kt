package ru.iteye.androidlivecourseapp.presentation.ui.auth_email

import android.util.Log
import android.widget.EditText
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailPresenter
import ru.iteye.androidlivecourseapp.presentation.ui.friends_list.FriendsListActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailView


class AuthEmailActivity : BaseActivity(), AuthEmailView {


    private val authEmailPresenter = AuthEmailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)

        authEmailPresenter.setView(this)
        Log.d(TAG, "AuthEmail created!")
    }

    override fun showError(message: String) {
        showCustomAlert(message)
    }

    fun onBtnClickAuth(view: View){
        Log.d("***","AuthEmailActivity -> onBtnClickAuth")

        if (validateFormEmailAuth()) {
            val email = this.findViewById<EditText>(R.id.input_email).text.toString()
            val password = this.findViewById<EditText>(R.id.input_password).text.toString()
            authEmailPresenter.signIn(email, password)
        }
    }

    private fun validateFormEmailAuth(): Boolean {

        val email = this.findViewById<EditText>(R.id.input_email)
        val password = this.findViewById<EditText>(R.id.input_password)

        if (TextUtils.isEmpty(email.text.toString()) || !authEmailPresenter.isValidEmail(email.text)) {
            onWrongEmail(email)
            return false
        }

        if (TextUtils.isEmpty(password.text.toString()) || password.text.length<6) {
            onWrongPassword(password)
            return false
        }

        return true
    }

    override fun onWrongEmail(email: EditText) {
        email.error = "Required correct email!"
    }

    override fun onWrongPassword(password: EditText) {
        password.error = "Must be 6 symbols or more!"
    }

    override fun onUserRegistered(){
        showMessage("New user registered!", true)
        onSuccessAuth()
    }

    override fun onSuccessAuth() {
        val intent = Intent(this, FriendsListActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun onFailedAuth() {
        showError("Something wrong while authentication!")
    }

}


