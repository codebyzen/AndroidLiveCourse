package ru.iteye.androidcourseproject01.presentation.authemail

import android.text.TextUtils
import android.util.Log
import ru.iteye.androidcourseproject01.domain.auth.AuthInteractor
import ru.iteye.androidcourseproject01.presentation.global.BasePresenter
import ru.iteye.androidcourseproject01.repositories.auth.AuthRepositoryImpl


class AuthEmailPresenter: BasePresenter<AuthEmailActivity>() {

    private var interactor = AuthInteractor(AuthRepositoryImpl())

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
        Log.d("***", "AuthEmailPresenter -> signIn")
        interactor.authByMail(email, password, ::afterRegistration)

    }


    fun afterRegistration(isNewUser: Boolean?) {

        when (isNewUser) {
            true -> onUserRegistered()
            false -> onUserAuthenticated()
            null -> onUserAuthError()
        }
        Log.d("***", "AuthEmailPresenter -> afterRegistration -> success -> "+isNewUser.toString())

    }

    fun onUserRegistered() {
        getView()?.onUserRegistered()
    }

    fun onUserAuthenticated() {
        getView()?.onSuccessAuth()
    }

    fun onUserAuthError() {
        getView()?.onFailedAuth()
    }

}