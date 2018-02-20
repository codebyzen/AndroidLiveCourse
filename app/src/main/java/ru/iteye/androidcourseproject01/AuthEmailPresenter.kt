package ru.iteye.androidcourseproject01

import android.text.TextUtils
import android.util.Log


class AuthEmailPresenter(view: AuthEmailActivity) {

    private val authRepository = AuthEmailRepository()

    private val view: AuthEmailActivity = view

    /**
     * Проверяем на валидность email
     */
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    /**
     * Пытаемся войти с предоставленными данными
     * Если не получается (нет пользователя) то регистрируем нового
     */
    fun signIn(email: String, password: String) {
        Log.d("***", "signIn:" + email)

        this.authRepository.signInEmail(email, password, ::afterRegistration)

    }


    fun afterRegistration(isNewUser: Boolean?) {

        when (isNewUser) {
            true -> view.showMessage("New user registered!", false)
            false -> view.showMessage("User authenticated!", false)
            null -> view.showMessage("Something wrong!!!", false)
        }
        Log.d("afterRegistration", isNewUser.toString())

    }



}