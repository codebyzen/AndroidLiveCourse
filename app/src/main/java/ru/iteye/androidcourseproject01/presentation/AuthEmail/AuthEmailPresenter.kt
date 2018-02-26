package ru.iteye.androidcourseproject01.presentation.authemail

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.util.Log
import ru.iteye.androidcourseproject01.domain.auth.AuthInteractor
import ru.iteye.androidcourseproject01.presentation.authchoose.AuthChooseActivity
import ru.iteye.androidcourseproject01.repositories.auth.AuthRepositoryImpl


class AuthEmailPresenter {
    private var interactor = AuthInteractor(AuthRepositoryImpl())


    private var view : AuthEmailActivity? = null

    fun getView() : AuthEmailActivity? {
        return view
    }

    fun setView(view : AuthEmailActivity?){
        this.view = view
    }

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