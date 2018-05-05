package ru.iteye.androidlivecourseapp.data.firebase

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import ru.iteye.androidlivecourseapp.utils.gson_classes.FirebaseInternalError

class AuthCheck {

    var gson = Gson()
    var fAuth: FirebaseAuth? = null

    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Проверяем авторизован ли уже пользователь
     */
    fun authCheck(listener: TaskAuthFirebaseListener) {
        Log.d("***", "FirebaseUserPreferences -> authCheck")

        fAuth = FirebaseAuth.getInstance()

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
            is FirebaseAuthInvalidUserException -> {
                listener.onError(FirebaseExpectionUtil(exception.errorCode, ErrorsTypes.valueOf(exception.errorCode)))
            }
            is FirebaseException -> {
                val message = exception.message.toString()
                val json = message.substring(message.indexOf("{"),message.lastIndexOf("}") + 1)
                val error: FirebaseInternalError.Response = gson.fromJson(json, FirebaseInternalError.Response::class.java)
                listener.onError(FirebaseExpectionUtil(error.error?.message!!, ErrorsTypes.valueOf(error.error.message)))
            }
        }
    }

}