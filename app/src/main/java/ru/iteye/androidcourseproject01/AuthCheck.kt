package ru.iteye.androidcourseproject01

import android.content.Context
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

        this.mAuth = FirebaseAuth.getInstance()
        this.user = mAuth?.currentUser
        var resultIs: Boolean = false
        if (this.user==null) {
            resultIs = false
        } else {
            resultIs = true
        }
        return resultIs
    }
}