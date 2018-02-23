package ru.iteye.androidcourseproject01.presentation.splashscreen

import android.os.Handler
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import ru.iteye.androidcourseproject01.data.FirebaseImpl
import ru.iteye.androidcourseproject01.domain.AuthEmail.AuthEmailRepository


//TODO: ну непонимаю я как передавать view... Так как сейчас работает...
class AppPresenter(view: AppActivity) {

    private val view: AppActivity = view

    /**
     * Возвращаем true если версия Google Play Service нормальная
     */
    private fun checkForGooglePlayServiceVersion(): Boolean {
        // Берем Google Play Service версии
        val googlePlayServiceCurrentVersion = view.packageManager.getPackageInfo("com.google.android.gms", 0).versionCode
        val googlePlayServiceNewestVersion = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE

        Log.d("*** VERSION CODE have", googlePlayServiceCurrentVersion.toString())
        Log.d("*** VERSION CODE need", googlePlayServiceNewestVersion.toString())
        return googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
    }

    /**
     * Смотрим что у нас с авторизацией
     */
    private fun checkForAuth(){
        // тут у нас проверка на аутентификацию
        val authDomain = AuthEmailRepository()

        authDomain.checkAuth(::afterAuthCheck)
    }

    fun afterAuthCheck(isAuth: Boolean) {
        if (isAuth) {
            view.showMessage("Authentication success!", false)
            //TODO: тут мы можем переходить к списку друзей
        } else {
            // не аутентифицирован
            view.showMessage("Authentication failed!", false)
            view.startAuthChooseActivity()
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
                view.showCustomAlert("Update Google Services!", true)
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