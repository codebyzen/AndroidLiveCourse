package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable

interface RegRepository {
    fun registrationByEmail(email : String, password : String): Observable<Boolean>
}