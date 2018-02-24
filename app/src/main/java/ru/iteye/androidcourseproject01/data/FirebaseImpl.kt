package ru.iteye.androidcourseproject01.data

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//TODO а куда тут пере

open class FirebaseImpl {
    var fAuth: FirebaseAuth? = null // класс авторизации firebase
    var fUser: FirebaseUser? = null // класс пользователя firebase

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail(){
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
        Log.d("***", "AuthCheck checkAuth")
        //TODO тут эта функа возвращает promise надо разобраться что это и как обрабатывать
        FirebaseAuth.getInstance().currentUser?.reload() // перегружаем чтобы из сессии не тянул
        this.fAuth = FirebaseAuth.getInstance()
        this.fUser = fAuth?.currentUser
        if (this.fUser==null) {
            afterAuthCheck(false)
        } else {
            afterAuthCheck(true)
        }
    }

}