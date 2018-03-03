package ru.iteye.androidcourseproject01.domain.auth

import android.util.Log
import ru.iteye.androidcourseproject01.domain.global.repositories.AuthRepository

//TODO этот парень уже знает, что есть модель, которая умеет авторизовывать
//TODO обычно, это репозиторий. Для упрощения, я инициализирую репозиторий прям через конструктор, но позже мы сделаем чуть-чуть иначе
class AuthInteractor(private var repository : AuthRepository) {

    fun authByMail(email : String, password : String, afterRegistration: (Boolean?) -> Unit) {
        Log.d("***", "AuthInteractor -> authByMail")
        repository.authByMail(email, password, afterRegistration)
    }

}
