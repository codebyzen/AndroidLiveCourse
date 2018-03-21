package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes

interface RegRepository {
    fun registrationByEmail(email : String, password : String): Observable<ErrorsTypes>
}