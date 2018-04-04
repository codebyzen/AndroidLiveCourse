package ru.iteye.androidlivecourseapp.data.database.firebase_auth

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.data.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import com.google.firebase.auth.FirebaseAuthException
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.internal.api.FirebaseNoSignedInUserException


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

        task.addOnCompleteListener(OnCompleteListener {
            if (!it.isSuccessful) {
                try {
                    if(task.exception != null) {
                        throw task.exception!!
                    }
                    else {
                        Log.d("***", "signUp: task is null")
                    }
                }
                catch(e: FirebaseAuthInvalidCredentialsException) {
                    Log.d("***", "FirebaseAuthInvalidCredentialsException: ${e.errorCode}")
                }
                catch(e: FirebaseAuthInvalidUserException) {
                    Log.d("***", "FirebaseAuthInvalidUserException: ${e.errorCode}")
                }
                catch(e: Exception) {
                    // ловит только это....
                    // возвращает вот что
                    //                    An internal error has occurred. [ {
                    //                        "error":{
                    //                        "code": 400,
                    //                        "message": "USER_DISABLED",
                    //                        "errors": [
                    //                        {
                    //                            "message": "USER_DISABLED",
                    //                            "domain": "global",
                    //                            "reason": "invalid"
                    //                        }
                    //                        ]
                    //                    }
                    //                    } ]
                    Log.d("***", "signUp: simple Exception")
                }
            } else {
                Log.d("***", "FirebaseAuth -> authCheck -> onComplete")
                listener.onComplete()
            }
        })
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