package ru.iteye.androidlivecourseapp.repositories

import android.content.Context
import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.SplashRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.GooglePlayUtils
import android.net.ConnectivityManager
import com.google.firebase.auth.AuthResult
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.models.Application
import ru.iteye.androidlivecourseapp.data.firebase.AuthCheck
import com.vk.sdk.util.VKUtil




class SplashRepositoryImpl: SplashRepository {

    private val firebasecheck = AuthCheck()

    private fun checkForGooglePlayServiceVersion(): Boolean {
        Log.d("***", "SplashRepositoryImpl -> checkForGooglePlayServiceVersion")


        val googlePlayServiceCurrentVersion = GooglePlayUtils.getGooglePlayCurrentVersion()
        Log.d("***", "SplashRepositoryImpl -> checkForGooglePlayServiceVersion -> googlePlayServiceCurrentVersion = " + googlePlayServiceCurrentVersion.toString())
        val googlePlayServiceNewestVersion = GooglePlayUtils.getGooglePlayNewestVersion()
        Log.d("***", "SplashRepositoryImpl -> checkForGooglePlayServiceVersion -> googlePlayServiceNewestVersion = " + googlePlayServiceNewestVersion.toString())

        if (googlePlayServiceCurrentVersion == null) {
            return false
        }

        if (googlePlayServiceNewestVersion == null) {
            return true
        }

        val gpsCurrent = googlePlayServiceCurrentVersion.toInt()
        val gpsActual = googlePlayServiceNewestVersion.toInt()

        return gpsCurrent >= gpsActual
    }

    private fun isConnected(): Boolean {
        val cm = Application.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }


    override fun startupCheck(): Observable<Boolean> {
        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "SplashRepositoryImpl -> startupCheck -> thread name: " + Thread.currentThread().name)
            try {
                if (!checkForGooglePlayServiceVersion()) {
                    Log.d("***", "SplashRepositoryImpl -> startupCheckList -> GooglePlayServiceVersion need up to date!")
                    subscriber.onError(Exception(ErrorsTypes.GOOGLEPLAYSERVICE_OUTDATE.toString()))
                } else {
                    if (!isConnected()) {
                        Log.d("***", "SplashRepositoryImpl -> startupCheckList -> No Internet Connection!")
                        subscriber.onError(Exception(ErrorsTypes.NO_INTERNET_CONNECTION.toString()))
                    } else {
                        Log.d("***", "SplashRepositoryImpl -> startupCheckList -> complete")

                        firebasecheck.authCheck(object : TaskAuthFirebaseListener {
                            override fun onSuccess(result: AuthResult) {
                                Log.d("***", "SplashRepositoryImpl -> startupCheck:override -> onSuccess")
                                subscriber.onNext(true)
                                subscriber.onComplete()
                            }
                            override fun onComplete() {
                                Log.d("***", "SplashRepositoryImpl -> startupCheck:override -> onComplete")
                                subscriber.onNext(true)
                                subscriber.onComplete()
                            }
                            override fun onError(exception: Exception?) {
                                Log.d("***", "SplashRepositoryImpl -> startupCheck:override -> onError")
                                subscriber.onError(exception)
                            }
                        })
                    }
                }
            } catch (e: Exception) {
                Log.d("***", "SplashRepositoryImpl -> startupCheckList -> Exception: " + e.message.toString())
                subscriber.onError(e)
            }


        }
    }


}