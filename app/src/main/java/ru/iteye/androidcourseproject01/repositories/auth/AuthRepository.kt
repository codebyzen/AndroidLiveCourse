package ru.iteye.androidcourseproject01.repositories.auth

interface AuthRepository {
    fun authByMail(email : String, password : String, afterRegistration: (Boolean?) -> Unit)
    fun registrationByEmail(email: String, password: String, afterRegistration: (Boolean?) -> Unit)
    fun checkAuth(afterCheck: (Boolean?) -> Unit)
}