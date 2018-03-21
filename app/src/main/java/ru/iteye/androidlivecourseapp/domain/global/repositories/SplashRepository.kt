package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes

interface SplashRepository {
    fun startupCheck(): Observable<ErrorsTypes>
}