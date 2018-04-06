package ru.iteye.androidlivecourseapp.data.database.firebase_auth

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.data.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import com.google.firebase.auth.FirebaseAuthException
import com.google.gson.Gson
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import ru.iteye.androidlivecourseapp.utils.gson_classes.DeserializationFirebaseInternalError


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
                        throw it.exception!!
                    }
                    else {
                        Log.d("***", "signUp: task is null")
                        listener.onError(FirebaseExpectionUtil("USER_IS_NULL", ErrorsTypes.USER_IS_NULL))
                    }
                }

                catch(e: FirebaseAuthInvalidUserException) {
                    Log.d("***", "FirebaseAuthInvalidUserException: ${e.errorCode}")
                    listener.onError(FirebaseExpectionUtil(e.errorCode.toString(), ErrorsTypes.valueOf(e.errorCode.toString())))
                }
                catch(e: Exception) {

                    val errorMessage = e.message.toString()
                    val jsonToNormalStr = errorMessage.substring(errorMessage.indexOf("{"),errorMessage.lastIndexOf("}") + 1)
                    val mMineUserEntity: DeserializationFirebaseInternalError.Response = gson.fromJson(jsonToNormalStr, DeserializationFirebaseInternalError.Response::class.java)
                    val errorCode = mMineUserEntity.error?.message.toString()

                    listener.onError(FirebaseExpectionUtil(errorCode, ErrorsTypes.valueOf(errorCode)))
                    Log.d("***", "simple Exception: " + errorCode)
                }
            } else {
                val currentUserReloaded: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                if (currentUserReloaded?.isEmailVerified == false) {
                    currentUserReloaded.sendEmailVerification()
                }
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