package ru.iteye.androidcourseproject01

import android.util.Log
import android.widget.EditText
import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View


class AuthEmailActivity : BaseActivity() {

    private val authEmailPresenter = AuthEmailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)
        setContext(this) // задаем контекст нашему BaseActivity
        Log.d(TAG, "AuthEmail created!")
    }

    //TODO, отлично! Только это можно и в BaseActivity перенести. Ведь у тебя ошибка может быть везде, а не только тут?
    fun showError(msg: String){
       showToast(msg)
    }

    fun onBtnClickAuth(view: View){
        Log.d("CLICK","onBtnClickAuth click")
        val email = this.findViewById<EditText>(R.id.input_email)
        val password = this.findViewById<EditText>(R.id.input_password)

        authEmailPresenter.signIn(email, password)
    }



}


