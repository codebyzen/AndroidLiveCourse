package ru.iteye.androidlivecourseapp.domain.global.repositories

interface AuthRepository {
    fun authByMail(email : String, password : String, afterRegistration: (Boolean?) -> Unit)
    fun registrationByEmail(email: String, password: String, afterRegistration: (Boolean?) -> Unit)
    fun checkAuth(afterCheck: (Boolean?) -> Unit)
}