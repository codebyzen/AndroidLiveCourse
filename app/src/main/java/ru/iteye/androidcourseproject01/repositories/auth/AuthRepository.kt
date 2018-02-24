package ru.iteye.androidcourseproject01.repositories.auth

interface AuthRepository {
    fun authByMail(mail : String, pass : String)
}