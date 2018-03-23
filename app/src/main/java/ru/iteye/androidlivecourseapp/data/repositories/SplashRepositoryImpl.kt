package ru.iteye.androidlivecourseapp.data.repositories

import android.content.Context
import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.SplashRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.GooglePlayUtils
import android.net.ConnectivityManager
import ru.iteye.androidlivecourseapp.domain.global.models.Application


class SplashRepositoryImpl: SplashRepository {


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

        Log.d("***", "SplashRepositoryImpl -> checkForGooglePlayServiceVersion : $googlePlayServiceCurrentVersion >= $googlePlayServiceNewestVersion")
        return googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
    }

    private fun isConnected(): Boolean {
        val cm = Application.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun startupCheck(): Observable<ErrorsTypes> {
        return Observable.create<ErrorsTypes> { subscriber ->
            Log.d("***", "SplashRepositoryImpl -> startupCheck -> Observable.create")
            Log.d("***", "SplashRepositoryImpl -> startupCheck -> thread name: " + Thread.currentThread().name)

            try {

                if (!checkForGooglePlayServiceVersion()) {
                    Log.d("***", "SplashRepositoryImpl -> startupCheckList -> GooglePlayServiceVersion need up to date!")
                    subscriber.onNext(ErrorsTypes.GOOGLEPLAYERROR)
                    subscriber.onComplete()
                } else {
                    // TODO: ждем интернета, но видимо лучше как-то по другому это реализовать
                    if (!isConnected()) {
                        //TODO: тут как-то можно вывести информацию для пользователя о том, что необходимо включить интернет
                    }
                    while(!isConnected()) {
                        Thread.sleep(1_000)
                        Log.d("***", "SplashRepositoryImpl -> startupCheckList -> Wait for internet")
                    }

                    fun test(result: ErrorsTypes){
                        Log.d("***", "SplashRepositoryImpl -> startupCheck -> test -> result: " + result.toString())
                        subscriber.onNext(result)
                        subscriber.onComplete()
                    }

                    val authRepositoryImpl = AuthRepositoryImpl()
                    //val authResult = authRepositoryImpl.checkAuth()

                    authRepositoryImpl.checkAuthTest(::test)


                }


            } catch (e: Exception) {
                Log.d("***", "SplashRepositoryImpl -> startupCheckList -> Exception: " + e.message.toString())
                subscriber.onNext(ErrorsTypes.EXECUTIONEXCEPTION)
                subscriber.onError(e)
            }


        }
    }

}