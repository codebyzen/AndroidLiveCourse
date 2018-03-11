package ru.iteye.androidlivecourseapp.domain.auth

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository

//TODO этот парень уже знает, что есть модель, которая умеет авторизовывать
//TODO обычно, это репозиторий. Для упрощения, я инициализирую репозиторий прям через конструктор, но позже мы сделаем чуть-чуть иначе
class AuthInteractor(private var repository : AuthRepository) {

    fun authByMail(email : String, password : String): Observable<Boolean?> {
        Log.d("***", "AuthInteractor -> authByMail")
        return repository.authByMail(email, password)
    }

}
