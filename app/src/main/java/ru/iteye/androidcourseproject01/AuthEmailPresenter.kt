package ru.iteye.androidcourseproject01

import android.text.TextUtils
import android.util.Log
import android.widget.EditText


class AuthEmailPresenter(connectedActivity: AuthEmailActivity) {

    val activity: AuthEmailActivity = connectedActivity

    /**
     * Проверяем на валидность email
     */
    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    /**
     * Проверяем на валидность форму логина/пароля/чекбокса
     */
    private fun validateFormEmailAuth(emailField: EditText, passwordField: EditText): Boolean {

        if (TextUtils.isEmpty(emailField.text.toString()) || !isValidEmail(emailField.text)) {
            emailField.error = "Required correct email!"
            return false
        }

        if (TextUtils.isEmpty(passwordField.text.toString()) || passwordField.text.length<6) {
            passwordField.error = "Must be 6 symbols or more!"
            return false
        }

        return true
    }


    /**
     * Пытаемся войти с предоставленными данными
     * Если не получается (нет пользователя) то регистрируем нового
     */
    fun signIn(emailField: EditText, passwordField: EditText) {
        Log.d("***", "signIn:" + emailField.text.toString())

        if (validateFormEmailAuth(emailField, passwordField)) {

            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            val authRepository = AuthEmailRepository()
            authRepository.signInEmail(email, password)


        }


    }


}