package ru.iteye.androidlivecourseapp.data.database.firebase_auth

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


open class FirebaseAuth {
    var fAuth: FirebaseAuth? = null
    var fUser: FirebaseUser? = null


    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail(){
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
    fun checkAuth(afterAuthCheck: (Boolean) -> Unit) {
        Log.d("***", "FirebaseAuth -> checkAuth")


        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            Log.d("***", "FirebaseAuth -> checkAuth -> currentUser is NULL")
            afterAuthCheck(false)
        }

        Log.d("***", "FirebaseAuth -> checkAuth -> currentUser = "+currentUser.toString())

        val task = currentUser?.reload()

        Log.d("***", "FirebaseAuth -> checkAuth -> task = "+task.toString())

        //TODO: тут почему-то работает неправильно, вызывается одно за другим, потом разберусь
        task?.addOnCompleteListener {
            Log.d("***", "FirebaseAuth -> checkAuth -> addOnSuccessListener")
            this.fUser = fAuth?.currentUser
            afterAuthCheck(true)
        }?.addOnFailureListener{
            Log.d("***", "FirebaseAuth -> checkAuth -> addOnFailureListener")
            afterAuthCheck(false)
        }


    }

}