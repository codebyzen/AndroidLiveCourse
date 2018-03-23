package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

interface RegRepository {
    fun registrationByEmail(email : String, password : String): Observable<ErrorsTypes>
}