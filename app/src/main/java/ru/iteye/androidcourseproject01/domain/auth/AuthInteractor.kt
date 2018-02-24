package ru.iteye.androidcourseproject01.domain.auth

import ru.iteye.androidcourseproject01.repositories.auth.AuthRepository

// private var repository
//TODO этот парень уже знает, что есть модель, которая умеет авторизовывать
//TODO обычно, это репозиторий. Для упрощения, я инициализирую репозиторий прям через конструктор, но позже мы сделаем чуть-чуть иначе
class AuthInteractor(private var repository : AuthRepository) {
    fun authByMail(mail : String, pass : String) {
        repository.authByMail(mail, pass)
    }
}
