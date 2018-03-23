package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

interface AuthRepository {
    fun authByMail(email : String, password : String): Observable<Boolean>
    fun checkAuth(): ErrorsTypes
    fun checkAuthTest(callback: (result: ErrorsTypes) -> Unit)
}