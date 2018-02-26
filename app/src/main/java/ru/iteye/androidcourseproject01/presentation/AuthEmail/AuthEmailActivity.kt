package ru.iteye.androidcourseproject01.presentation.authemail

import android.util.Log
import android.widget.EditText
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.authchoose.AuthChooseActivity
import ru.iteye.androidcourseproject01.presentation.friendslist.FriendsListActivity
import ru.iteye.androidcourseproject01.presentation.view.authemail.AuthEmailView


class AuthEmailActivity : BaseActivity(), AuthEmailView {


    private val authEmailPresenter = AuthEmailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)

        authEmailPresenter.setView(this)
        Log.d(TAG, "AuthEmail created!")
    }

    fun onBtnClickAuth(view: View){
        Log.d("***","AuthEmailActivity -> onBtnClickAuth")

        if (validateFormEmailAuth()) {
            val email = this.findViewById<EditText>(R.id.input_email).text.toString()
            val password = this.findViewById<EditText>(R.id.input_password).text.toString()
            authEmailPresenter.signIn(email, password)
        }
    }

    /**
     * Проверяем на валидность форму логина/пароля/чекбокса
     */
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
        showCustomAlert("Something wrong while authentication!")
    }

}


