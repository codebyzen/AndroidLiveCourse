package ru.iteye.androidlivecourseapp.data.database.firebase_auth

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import com.google.firebase.auth.FirebaseAuthException
import com.google.gson.Gson
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import ru.iteye.androidlivecourseapp.utils.gson_classes.FirebaseInternalError


open class FirebaseAuth {
    var fAuth: FirebaseAuth? = null
    var fUser: FirebaseUser? = null
    var gson = Gson()

    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail() {
        Log.d("***", "FirebaseAuth -> sendVerifyEmail")
        if (fUser != null) {
            fUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Email sent.")
                }
            }
        }
    }

    /**
     * Проверяем авторизован ли уже пользователь и выполняем замыкание
     */
    fun authCheck(listener: TaskAuthFirebaseListener) {
        Log.d("***", "FirebaseAuth -> authCheck")

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            listener.onError(FirebaseExpectionUtil("ERROR_USER_NOT_FOUND", ErrorsTypes.ERROR_USER_NOT_FOUND))
            return
        }

        val task = currentUser.reload()

        task.addOnFailureListener {
            handleAuthException(it, listener)
        }

        task.addOnCompleteListener({
            if(it.isSuccessful) {
                val currentUserReloaded: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                if (currentUserReloaded?.isEmailVerified == false) {
                    currentUserReloaded.sendEmailVerification()
                }
                Log.d("***", "FirebaseAuth -> authCheck -> onComplete")
                listener.onComplete()
            }
        })
    }

    private fun handleAuthException(exception : Exception, listener: TaskAuthFirebaseListener) {
        exception.printStackTrace()
        when (exception){
            is FirebaseException -> {
                val message = exception.message.toString()
                val json = message.substring(message.indexOf("{"),message.lastIndexOf("}") + 1)
                val error: FirebaseInternalError.Response =
                        gson.fromJson(json, FirebaseInternalError.Response::class.java)
                listener.onError(FirebaseExpectionUtil(error.error?.message!!, ErrorsTypes.ERROR_INVALID_CUSTOM_TOKEN))

            }
            is FirebaseAuthInvalidUserException -> {
                listener.onError(FirebaseExpectionUtil(exception.errorCode, ErrorsTypes.valueOf(exception.errorCode)))
            }
        }
    }


    fun authByMail(email: String, password: String, listener: TaskAuthFirebaseListener) {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        val task = fAuth?.signInWithEmailAndPassword(email, password)


        if (task == null) {
            Log.d("***", "FirebaseAuth -> authByMail -> currentUser is NULL")
            listener.onError(FirebaseExpectionUtil("USER_IS_NULL", ErrorsTypes.USER_IS_NULL))
            return
        }

        task.addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful)
                listener.onSuccess(it.result)
            else {
                val errorCode = (it?.exception as FirebaseAuthException).errorCode
                Log.d("***", "FirebaseAuth -> authByMail -> errorCode: $errorCode")
                Log.d("***", "FirebaseAuth -> authByMail -> Exception: " + it.exception.toString())
                Log.d("***", "FirebaseAuth -> authByMail -> ErrorsTypes.valueOf: " + ErrorsTypes.valueOf(errorCode).toString())
                listener.onError(FirebaseExpectionUtil(errorCode, ErrorsTypes.valueOf(errorCode)))
            }
        }).addOnFailureListener({
            Log.d("***", "FirebaseAuth -> authByMail -> Exception: " + it.message.toString())
        })
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
                listener.onSuccess(it.result)
            } else {
                val errorCode = (it?.exception as FirebaseAuthException).errorCode
                Log.d("***", "FirebaseAuth -> regByMail -> errorCode: $errorCode")
                Log.d("***", "FirebaseAuth -> regByMail -> Exception: " + it.exception.toString())
                Log.d("***", "FirebaseAuth -> regByMail -> ErrorsTypes.valueOf: " + ErrorsTypes.valueOf(errorCode).toString())
                listener.onError(FirebaseExpectionUtil(errorCode, ErrorsTypes.valueOf(errorCode)))
            }
        }).addOnFailureListener({
            Log.d("***", "FirebaseAuth -> regByMail -> Exception: " + it.message.toString())
        })

    }
}