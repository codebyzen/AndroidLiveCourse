package ru.iteye.androidlivecourseapp.presentation.mvp.auth_email

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.domain.auth.AuthInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.data.repositories.AuthRepositoryImpl
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity
import java.lang.Thread.currentThread


class AuthEmailPresenter: BasePresenter<AuthEmailActivity>() {

    private var interactor = AuthInteractor(AuthRepositoryImpl())

    fun signIn(email: String, password: String) {
        Log.d("***", "AuthEmailPresenter -> signIn")

        disposables.add(
            interactor.authByMail(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ isAuthSuccess ->
                        Log.d("***", "AuthEmailPresenter -> signIn -> thread name: " + currentThread().name)
                        Log.d("***", "AuthEmailPresenter -> observerListener -> observableAuthResult: " + isAuthSuccess.toString())
                        afterAuthentification(isAuthSuccess)
                    })
        )
    }

    private fun afterAuthentification(isUserAuthSuccess: Boolean?) {

        Log.d("***", "AuthEmailPresenter -> afterAuthentification -> "+isUserAuthSuccess.toString())

        when (isUserAuthSuccess) {
            true -> onUserAuthenticated()
            false -> onUserAuthError()
            null -> onFirebaseAuthError()
        }
    }



    private fun onFirebaseAuthError(){
        getView()?.onFailedFirebaseAuth()
    }

    private fun onUserAuthenticated() {
        getView()?.onSuccessAuth()
    }

    private fun onUserAuthError() {
        getView()?.onFailedAuth()
    }

}