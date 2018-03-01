package ru.iteye.androidcourseproject01.presentation.splashscreen

import android.os.Handler
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import ru.iteye.androidcourseproject01.presentation.global.BasePresenter
import ru.iteye.androidcourseproject01.presentation.view.splash.SplashView
import ru.iteye.androidcourseproject01.repositories.auth.AuthRepositoryImpl


class SplashPresenter: BasePresenter<SplashView>() {

    /**
     * Возвращаем true если версия Google Play Service нормальная
     */
    private fun checkForGooglePlayServiceVersion(): Boolean {
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion")
        // Берем Google Play Service версии
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion -> getView() = "+getView().toString())

        val googlePlayServiceCurrentVersion = getContext()?.packageManager?.getPackageInfo("com.google.android.gms", 0)?.versionCode
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion -> googlePlayServiceCurrentVersion = "+googlePlayServiceCurrentVersion.toString())
        val googlePlayServiceNewestVersion = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion -> googlePlayServiceNewestVersion = "+googlePlayServiceNewestVersion.toString())

        if (googlePlayServiceCurrentVersion == null) {
            return false
        }


        Log.d("*** VERSION CODE need", googlePlayServiceNewestVersion.toString())
        Log.d("***", "SplashPresenter -> checkForGooglePlayServiceVersion : $googlePlayServiceCurrentVersion >= $googlePlayServiceNewestVersion")
        return googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
    }

    /**
     * Смотрим что у нас с авторизацией
     */
    private fun checkForAuth(){
        Log.d("***", "SplashPresenter -> checkForAuth")
        // тут у нас проверка на аутентификацию
        val authRepositoryImpl = AuthRepositoryImpl()

        authRepositoryImpl.checkAuth(::afterAuthCheck)
    }

    fun afterAuthCheck(isAuth: Boolean?) {
        Log.d("***", "SplashPresenter -> afterAuthCheck")
        //TODO: при отсутствии пользователя показывается сначала тру потом фолс. Потом разберусь.
        if (isAuth==true) {
            Log.d("***", "SplashPresenter -> afterAuthCheck -> isAuth = true")
            //getView()?.showMessage("Authentication success!", true)
            getView()?.startFriendsListActivity()
        } else {
            Log.d("***", "SplashPresenter -> afterAuthCheck -> isAuth = false")
            //getView()?.showMessage("Authentication failed!", true)
            getView()?.startAuthChooseActivity()
        }
    }

    fun startupCheckList(){
        Log.d("***", "SplashPresenter -> startupCheckList")
        //TODO: реализовать асинхронность
        /**
         * Тут лучше использовать RxJava но я пока не соовсем рабозбрлся как
         * В перспективе пока мы показываем splash_screen например с заставкой или лого
         * асинхронно выполняются проверки checkForGooglePlayServiceVersion и checkForAuth
         */
        // подождем несколько секунд
        waitChecksResults(3000, {
            Log.d("***", "SplashPresenter -> startupCheckList -> waitChecksResults")
            // смотрим правильный ли у нас номер google Play Service
            if (!checkForGooglePlayServiceVersion()) {
                Log.d("***", "SplashPresenter -> startupCheckList -> GooglePlayServiceVersion need up to date!")
                // показываем диалог с просьбой обновить Google Services и выходим
                getView()?.showCustomAlertDialog("Update Google Services!")
            } else {
                // прошли проверку на версии
                checkForAuth()
            }
        })
    }


    /**
     * Держим активити несколько секунд, тут можем показать заставку или сделать что-то полезное
     * например проверить авторизацию, но лучше конечно создать отдельный поток
     * в контексте которого мы будем проверять все что нам надо, я описал это выше в туду,
     * а пока у нас тут офигенное замыкание =)
     */
    private fun waitChecksResults(mSecondsDelayed: Long, checksPayload: () -> Unit){
        Handler().postDelayed({
            checksPayload()
        }, mSecondsDelayed)
    }
}



