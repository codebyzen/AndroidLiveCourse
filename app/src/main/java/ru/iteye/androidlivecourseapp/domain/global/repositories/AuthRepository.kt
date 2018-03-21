package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes

interface AuthRepository {
    fun authByMail(email : String, password : String): Observable<ErrorsTypes>
    fun checkAuth(): ErrorsTypes
    fun checkAuthTest(callback: (result: ErrorsTypes) -> Unit)
}