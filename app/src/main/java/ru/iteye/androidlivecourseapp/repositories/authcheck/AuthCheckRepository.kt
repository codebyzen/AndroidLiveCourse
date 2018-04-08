package ru.iteye.androidlivecourseapp.repositories.authcheck

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import ru.iteye.androidlivecourseapp.data.database.firebaseuser.FirebaseUserPreferences
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import ru.iteye.androidlivecourseapp.utils.gson_classes.FirebaseInternalError

class AuthCheckRepository {

    var gson = Gson()
    var fAuth: FirebaseAuth? = null
    val applicationPrefs = FirebaseUserPreferences()

    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Проверяем авторизован ли уже пользователь и выполняем замыкание
     */
    fun authCheck(listener: TaskAuthFirebaseListener) {
        Log.d("***", "FirebaseUserPreferences -> authCheck")

        val currentUser = fAuth?.currentUser
        if (currentUser == null) {
            listener.onError(FirebaseExpectionUtil("ERROR_USER_NOT_FOUND", ErrorsTypes.ERROR_USER_NOT_FOUND))
            return
        }

        val task = currentUser.reload()

        task.addOnCompleteListener({
            if(it.isSuccessful) {
                val currentUserReloaded: FirebaseUser? = fAuth?.currentUser
                if (currentUserReloaded?.isEmailVerified == false) {
                    currentUserReloaded.sendEmailVerification()
                }
                Log.d("***", "FirebaseUserPreferences -> authCheck -> onComplete")
                listener.onComplete()
            }
        }).addOnFailureListener({
          handleAuthException(it, listener)
        })
    }


    private fun handleAuthException(exception : Exception, listener: TaskAuthFirebaseListener) {
        exception.printStackTrace()
        when (exception){
            is FirebaseException -> {
                val message = exception.message.toString()
                val json = message.substring(message.indexOf("{"),message.lastIndexOf("}") + 1)
                val error: FirebaseInternalError.Response = gson.fromJson(json, FirebaseInternalError.Response::class.java)
                listener.onError(FirebaseExpectionUtil(error.error?.message!!, ErrorsTypes.ERROR_INVALID_CUSTOM_TOKEN))

            }
            is FirebaseAuthInvalidUserException -> {
                listener.onError(FirebaseExpectionUtil(exception.errorCode, ErrorsTypes.valueOf(exception.errorCode)))
            }
        }
    }

}