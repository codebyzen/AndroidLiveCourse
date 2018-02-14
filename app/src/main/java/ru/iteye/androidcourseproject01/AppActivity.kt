package ru.iteye.androidcourseproject01

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.GoogleApiAvailability


/**
 * Наследовать опять же от BaseActivity
 */
open class AppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        Log.d("CREATE", "AppActivity")


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
                showCustomAlert("Update Google Services!", true)
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
            finish()
        }, mSecondsDelayed)
    }

    /**
     * Смотрим что у нас с авторизацией
     */
    private fun checkForAuth(){
        // тут у нас проверка на аутентификацию
        val authCheck = AuthCheck()

        if (!authCheck.checkAuth()) {
            // не аутентифицирован
            Toast.makeText(this, "Authentication failed!", Toast.LENGTH_LONG).show()

            // покажем активность с вариантами аутентификации
            val intent = Intent(this, ActivityAuthChoosePresenter::class.java).apply {}
            startActivity(intent)

        }
    }

    /**
     * Возвращаем true если версия Google Play Service нормальная
     */
    private fun checkForGooglePlayServiceVersion(): Boolean {
        var isOk: Boolean = false
        // Берем Google Play Service версии
        val googlePlayServiceCurrentVersion = packageManager.getPackageInfo("com.google.android.gms", 0).versionCode
        val googlePlayServiceNewestVersion = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE

        Log.d("*** VERSION CODE have", googlePlayServiceCurrentVersion.toString())
        Log.d("*** VERSION CODE need", googlePlayServiceNewestVersion.toString())
        isOk = googlePlayServiceCurrentVersion >= googlePlayServiceNewestVersion
        return isOk
    }

    /**
     * Simple alert dialog for some debug purposes
     */
    private fun showCustomAlert(customMessage: String, isExit: Boolean){
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog,null)
        val alertMessage = dialogView.findViewById<TextView>(R.id.textAlertMessage)
        dialog.setView(dialogView)
        //dialog.setCancelable(true)
        dialog.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->
            if (isExit) System.exit(0);
        })
        alertMessage.text = customMessage
        val customDialog = dialog.create()
        customDialog.show()

    }

}