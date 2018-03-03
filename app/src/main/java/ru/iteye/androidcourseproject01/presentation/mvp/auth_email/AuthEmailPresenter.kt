package ru.iteye.androidcourseproject01.presentation.mvp.auth_email

import android.text.TextUtils
import android.util.Log
import ru.iteye.androidcourseproject01.domain.auth.AuthInteractor
import ru.iteye.androidcourseproject01.presentation.mvp.global.BasePresenter
import ru.iteye.androidcourseproject01.data.repositories.AuthRepositoryImpl
import ru.iteye.androidcourseproject01.presentation.ui.auth_email.AuthEmailActivity


class AuthEmailPresenter: BasePresenter<AuthEmailActivity>() {

    private var interactor = AuthInteractor(AuthRepositoryImpl())

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun signIn(email: String, password: String) {
        Log.d("***", "AuthEmailPresenter -> signIn")
        interactor.authByMail(email, password, ::afterRegistration)

    }


    private fun afterRegistration(isNewUser: Boolean?) {

        when (isNewUser) {
            true -> onUserRegistered()
            false -> onUserAuthenticated()
            null -> onUserAuthError()
        }
        Log.d("***", "AuthEmailPresenter -> afterRegistration -> success -> "+isNewUser.toString())

    }

    private fun onUserRegistered() {
        getView()?.onUserRegistered()
    }

    private fun onUserAuthenticated() {
        getView()?.onSuccessAuth()
    }

    private fun onUserAuthError() {
        getView()?.onFailedAuth()
    }

}