package ru.iteye.androidlivecourseapp.domain.auth

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository

class AuthInteractor(private var repository : AuthRepository) {

    private val TAG = "*** AuthInteractor"

    fun authByMail(email : String, password : String): Observable<Boolean> {
        Log.d(TAG, " -> authByMail")
        return repository.authByMail(email, password)
    }

    fun authByToken(token: String): Observable<Boolean> {
        Log.d(TAG, " -> authByToken")
        return repository.authByToken(token)
    }

}
