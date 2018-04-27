package ru.iteye.androidlivecourseapp.data.firebase

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil

class RegEmail {

    var fAuth: FirebaseAuth? = null
    var fUser: FirebaseUser? = null

    init {
        fAuth = FirebaseAuth.getInstance()
    }

    fun regByMail(email: String, password: String, listener: TaskAuthFirebaseListener) {
        Log.d("***", "AuthRepositoryImpl -> regByMail ($email/$password)")

        val task = fAuth?.createUserWithEmailAndPassword(email, password)

        if (task == null) {
            listener.onError(FirebaseExpectionUtil("USER_IS_NULL", ErrorsTypes.USER_IS_NULL))
            return
        }

        task.addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful) {
                sendVerifyEmail()
                listener.onSuccess(it.result)
            } else {
                val errorCode = (it.exception as FirebaseAuthException).errorCode
                Log.d("***", "FirebaseUserPreferences -> regByMail -> errorCode: $errorCode")
                Log.d("***", "FirebaseUserPreferences -> regByMail -> Exception: " + it.exception.toString())
                Log.d("***", "FirebaseUserPreferences -> regByMail -> ErrorsTypes.valueOf: " + ErrorsTypes.valueOf(errorCode).toString())
                listener.onError(FirebaseExpectionUtil(errorCode, ErrorsTypes.valueOf(errorCode)))
            }
        }).addOnFailureListener({
            Log.d("***", "FirebaseUserPreferences -> regByMail -> Exception: " + it.message.toString())
        })

    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail() {
        Log.d("***", "FirebaseUserPreferences -> sendVerifyEmail")
        if (fUser != null) {
            fUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Email sent.")
                }
            }
        }
    }


}