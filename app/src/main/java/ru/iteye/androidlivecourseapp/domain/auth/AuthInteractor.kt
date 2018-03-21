package ru.iteye.androidlivecourseapp.domain.auth

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes

class AuthInteractor(private var repository : AuthRepository) {

    fun authByMail(email : String, password : String): Observable<ErrorsTypes> {
        Log.d("***", "AuthInteractor -> authByMail")
        return repository.authByMail(email, password)
    }

}
