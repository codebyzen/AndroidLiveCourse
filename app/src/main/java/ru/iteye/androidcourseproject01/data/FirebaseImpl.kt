package ru.iteye.androidcourseproject01.data

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


open class FirebaseImpl {
    var fAuth: FirebaseAuth? = null // класс авторизации firebase
    var fUser: FirebaseUser? = null // класс пользователя firebase


    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail(){
        Log.d("***", "FirebaseImpl -> sendVerifyEmail")
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
        Log.d("***", "FirebaseImpl -> checkAuth")


        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            Log.d("***", "FirebaseImpl -> checkAuth -> currentUser is NULL")
            afterAuthCheck(false)
        }

        Log.d("***", "FirebaseImpl -> checkAuth -> currentUser = "+currentUser.toString())

        val task = currentUser?.reload()

        Log.d("***", "FirebaseImpl -> checkAuth -> task = "+task.toString())

        //TODO: тут почему-то работает неправильно, вызывается одно за другим, потом разберусь
        task?.addOnCompleteListener {
            Log.d("***", "FirebaseImpl -> checkAuth -> addOnSuccessListener")
            this.fUser = fAuth?.currentUser
            afterAuthCheck(true)
        }?.addOnFailureListener{
            Log.d("***", "FirebaseImpl -> checkAuth -> addOnFailureListener")
            afterAuthCheck(false)
        }


    }

}