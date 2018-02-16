package ru.iteye.androidcourseproject01

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthCheck {

    var mAuth: FirebaseAuth? = null // смотрим в гугл авторизацию
    var user: FirebaseUser? = null

    //TODO: тут у нас будут проверки не только по email но и по социалкам
    /**
     * Возвращаем тру или фолс в зависимости от того авторизован пользователь или нет
     */
    fun checkAuth(): Boolean {
        Log.d("***", "AuthCheck checkAuth")
        FirebaseAuth.getInstance().currentUser?.reload() // перегружаем чтобы из сессии не тянул
        this.mAuth = FirebaseAuth.getInstance()
        this.user = mAuth?.currentUser
        return this.user != null
    }
}