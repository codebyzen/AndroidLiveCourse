package ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.repositories.SplashRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.splash.SplashInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil


class SplashPresenter : BasePresenter<SplashView>() {

    private var interactor = SplashInteractor(SplashRepositoryImpl())

    fun startupCheck(){
        Log.d("***", "SplashPresenter -> startupCheck")
        disposables.add(
                interactor.startupCheck()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ isPassedSuccess ->
                            Log.d("***", "SplashPresenter -> startupCheck -> thread name: " + Thread.currentThread().name)
                            Log.d("***", "SplashPresenter -> startupCheck -> observerListener -> observableAuthResult: " + isPassedSuccess.toString())
                            onAllChecksPassed()
                        }, { error ->
                            error.printStackTrace()
                            afterStartUpCheckError(error)
                        })
        )
    }

    private fun afterStartUpCheckError(error: Throwable) {
        Log.d("***", "SplashPresenter -> afterStartUpCheckError -> results = " + error.toString())
        if (error is FirebaseExpectionUtil) {
            Log.d("***", "SplashPresenter -> afterStartUpCheckError -> error is FirebaseExpectionUtil")
            when (error.type) {
                ErrorsTypes.ERROR_USER_NOT_FOUND -> onUserAuthError()
                ErrorsTypes.USER_NOT_FOUND -> onUserAuthError()
                ErrorsTypes.USER_DISABLED -> onUserDisabled()
                ErrorsTypes.ERROR_USER_DISABLED -> onUserDisabled()
                ErrorsTypes.USER_IS_NULL-> onUserAuthError()
            }
        } else {
            Log.d("***", "SplashPresenter -> afterStartUpCheckError -> error is NOT FirebaseExpectionUtil")
            when (error) {
                ErrorsTypes.NO_INTERNET_CONNECTION -> onNoInternet()
                ErrorsTypes.GOOGLEPLAYSERVICE_OUTDATE -> googlePlayServiceOutdate()
                ErrorsTypes.ALLOK -> getView()?.startFriendsListActivity()
                else -> {
                    getView()?.showError(error.message!!, {})
                }
            }
        }
    }

    private fun googlePlayServiceOutdate() {
        getView()?.googlePlayServiceOutdate()
    }

    private fun onNoInternet(){
        getView()?.onNoInternet()
    }

    private fun onAllChecksPassed() {
        Log.d("***", "SplashPresenter -> onAllChecksPassed")
        getView()?.startFriendsListActivity()
    }

    private fun onUserDisabled() {
        Log.d("***", "SplashPresenter -> onUserDisabled")
        getView()?.onUserDisabled()
    }

    private fun onUserAuthError() {
        Log.d("***", "SplashPresenter -> onUserAuthError")
        getView()?.startAuthChooseActivity()
    }

}



