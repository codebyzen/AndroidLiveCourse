package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable

interface AuthRepository {
    fun authByMail(email : String, password : String): Observable<Boolean>
}