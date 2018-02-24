package ru.iteye.androidcourseproject01.presentation.view.auth

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.login_screen_email.*
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.presentation.presenter.auth.AuthPresenter

class AuthActivity : BaseActivity(), AuthView{

    private val presenter = AuthPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)
        presenter.setView(this)

        button_auth.setOnClickListener({ authByMail() })

        Log.d(ContentValues.TAG, "AuthActivity created!")
    }

    private fun authByMail(){
        val mail = input_email.text.toString()
        val pass = input_password.text.toString()
        presenter.authByMail(mail, pass)
    }

    override fun onSuccessAuth() {
        showCustomAlert("Cool!")
    }


}