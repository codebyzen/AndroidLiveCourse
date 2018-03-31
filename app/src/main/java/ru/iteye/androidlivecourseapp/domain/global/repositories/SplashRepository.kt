package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

interface SplashRepository {
    fun startupCheck(): Observable<Boolean>
}