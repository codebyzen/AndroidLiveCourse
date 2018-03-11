package ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen

import android.os.Handler
import android.util.Log
import ru.iteye.androidlivecourseapp.data.repositories.AuthRepositoryImpl
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.GooglePlayUtils


class SplashPresenter : BasePresenter<SplashView>() {

    private fun checkForGooglePlayServiceVersion(): Boolean {
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion")


        val googlePlayServiceCurrentVersion = GooglePlayUtils.getGooglePlayCurrentVersion()
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion -> googlePlayServiceCurrentVersion = " + googlePlayServiceCurrentVersion.toString())
        val googlePlayServiceNewestVersion = GooglePlayUtils.getGooglePlayNewestVersion()
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion -> googlePlayServiceNewestVersion = " + googlePlayServiceNewestVersion.toString())

        if (googlePlayServiceCurrentVersion == null) {
            return false
        }

        if (googlePlayServiceNewestVersion == null) {
            return true
        }

        Log.d("*** VERSION CODE need", googlePlayServiceNewestVersion.toString())
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion : $googlePlayServiceCurrentVersion >= $googlePlayServiceNewestVersion")
        return googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
    }

    private fun checkForAuth() {
        Log.d("***", "SplashPresenter -> checkForAuth")
        val authRepositoryImpl = AuthRepositoryImpl()

        authRepositoryImpl.checkAuth(::afterAuthCheck)
    }

    private fun afterAuthCheck(isAuth: Boolean?) {
        Log.d("***", "SplashPresenter -> afterAuthCheck")
        //TODO: при отсутствии пользователя показывается сначала тру потом фолс. Потом разберусь.
        if (isAuth == true) {
            Log.d("***", "SplashPresenter -> afterAuthCheck -> isAuth = true")
            getView()?.startFriendsListActivity()
        } else {
            Log.d("***", "SplashPresenter -> afterAuthCheck -> isAuth = false")
            getView()?.startAuthChooseActivity()
        }
    }

    fun startupCheckList() {
        Log.d("***", "SplashPresenter -> startupCheckList")
        //TODO: реализовать асинхронность
        waitChecksResults(3000, {
            Log.d("***", "SplashPresenter -> startupCheckList -> waitChecksResults")
            if (!checkForGooglePlayServiceVersion()) {
                Log.d("***", "SplashPresenter -> startupCheckList -> GooglePlayServiceVersion need up to date!")
                getView()?.showError("Update Google Services!")
            } else {
                checkForAuth()
            }
        })
    }

    private fun waitChecksResults(mSecondsDelayed: Long, checksPayload: () -> Unit) {
        Handler().postDelayed({
            checksPayload()
        }, mSecondsDelayed)
    }
}



