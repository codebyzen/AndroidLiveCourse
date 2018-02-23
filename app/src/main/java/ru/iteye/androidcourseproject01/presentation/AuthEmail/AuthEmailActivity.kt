package ru.iteye.androidcourseproject01.presentation.AuthEmail

import android.util.Log
import android.widget.EditText
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.R


class AuthEmailActivity : BaseActivity() {

    private val authEmailPresenter = AuthEmailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)

        Log.d(TAG, "AuthEmail created!")
    }

    fun onBtnClickAuth(view: View){
        Log.d("CLICK","onBtnClickAuth click")

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
            email.error = "Required correct email!"
            return false
        }

        if (TextUtils.isEmpty(password.text.toString()) || password.text.length<6) {
            password.error = "Must be 6 symbols or more!"
            return false
        }

        return true
    }

}


