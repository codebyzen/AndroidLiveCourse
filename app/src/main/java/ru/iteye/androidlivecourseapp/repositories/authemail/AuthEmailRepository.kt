package ru.iteye.androidlivecourseapp.repositories.authemail

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil

class AuthEmailRepository {

    var fAuth: FirebaseAuth? = null

    init {
        fAuth = FirebaseAuth.getInstance()
    }

    fun authByMail(email: String, password: String, listener: TaskAuthFirebaseListener) {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        val task = fAuth?.signInWithEmailAndPassword(email, password)


        if (task == null) {
            Log.d("***", "FirebaseUserPreferences -> authByMail -> currentUser is NULL")
            listener.onError(FirebaseExpectionUtil("USER_IS_NULL", ErrorsTypes.USER_IS_NULL))
            return
        }

        task.addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful)
                listener.onSuccess(it.result)
            else {
                val errorCode = (it.exception as FirebaseAuthException).errorCode
                Log.d("***", "FirebaseUserPreferences -> authByMail -> errorCode: $errorCode")
                Log.d("***", "FirebaseUserPreferences -> authByMail -> Exception: " + it.exception.toString())
                Log.d("***", "FirebaseUserPreferences -> authByMail -> ErrorsTypes.valueOf: " + ErrorsTypes.valueOf(errorCode).toString())
                listener.onError(FirebaseExpectionUtil(errorCode, ErrorsTypes.valueOf(errorCode)))
            }
        }).addOnFailureListener({
            Log.d("***", "FirebaseUserPreferences -> authByMail -> Exception: " + it.message.toString())
        })
    }
}