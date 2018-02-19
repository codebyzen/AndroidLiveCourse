package ru.iteye.androidcourseproject01

import android.os.Handler
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability


class AppPresenter(connectedActivity: AppActivity) {
    //TODO презентер НИКОГДА! (ну почти=)) не знает о контексте. Для взаимодействия с активити надо делать view
    private val activity: AppActivity= connectedActivity


    /**
     * Возвращаем true если версия Google Play Service нормальная
     */
    fun checkForGooglePlayServiceVersion(): Boolean {
        var isOk = false
        // Берем Google Play Service версии
        val googlePlayServiceCurrentVersion = activity.packageManager.getPackageInfo("com.google.android.gms", 0).versionCode
        val googlePlayServiceNewestVersion = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE

        Log.d("*** VERSION CODE have", googlePlayServiceCurrentVersion.toString())
        Log.d("*** VERSION CODE need", googlePlayServiceNewestVersion.toString())
        isOk = googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
        return isOk
    }

    /**
     * Смотрим что у нас с авторизацией
     */
    fun checkForAuth(){
        // тут у нас проверка на аутентификацию
        val authDomain = AuthEmailRepository()

        if (authDomain.checkAuth()==null) {
            // не аутентифицирован
            activity.showError("Authentication failed!")

            activity.startAuthChooseActivity()

        } else {
            activity.showError("Authentication success!")
            //TODO: тут мы можем переходить к списку друзей
        }
    }


    fun startupCheckList(){
        //TODO: реализовать асинхронность
        /**
         * Тут лучше использовать RxJava но я пока не соовсем рабозбрлся как
         * В перспективе пока мы показываем activity_base например с заставкой или лого
         * асинхронно выполняются проверки checkForGooglePlayServiceVersion и checkForAuth
         */
        // подождем несколько секунд
        waitChecksResults(3000, {
            // смотрим правильный ли у нас номер google Play Service
            if (!checkForGooglePlayServiceVersion()) {
                // показываем диалог с просьбой обновить Google Services и выходим
                activity.showCustomAlert("Update Google Services!", true)
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
            //finish()
        }, mSecondsDelayed)
    }
}