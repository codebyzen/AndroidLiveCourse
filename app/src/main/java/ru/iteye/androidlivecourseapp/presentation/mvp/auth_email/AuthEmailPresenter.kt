package ru.iteye.androidlivecourseapp.presentation.mvp.auth_email

import android.text.TextUtils
import android.util.Log
import ru.iteye.androidlivecourseapp.domain.auth.AuthInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.data.repositories.AuthRepositoryImpl
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity



class AuthEmailPresenter: BasePresenter<AuthEmailActivity>() {

    private var interactor = AuthInteractor(AuthRepositoryImpl())

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


    fun signIn(email: String, password: String) {
        Log.d("***", "AuthEmailPresenter -> signIn")

        interactor.authByMail(email, password)
                .subscribe({ isAuthSuccess ->
                    Log.d("***", "AuthEmailPresenter -> observerListener -> observableAuthResult: " + isAuthSuccess.toString())
                    afterAuthentification(isAuthSuccess)
                })
    }


    private fun afterAuthentification(isUserAuthSuccess: Boolean?) {

        Log.d("***", "AuthEmailPresenter -> afterRegistration -> "+isUserAuthSuccess.toString())

        when (isUserAuthSuccess) {
            true -> onUserAuthenticated()
            false -> onUserAuthError()
            null -> onFirebaseAuthError()
        }
    }



    private fun onFirebaseAuthError(){
        getView()?.onFailedFirebaseAuth()
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