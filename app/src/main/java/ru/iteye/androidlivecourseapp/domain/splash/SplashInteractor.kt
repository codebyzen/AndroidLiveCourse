package ru.iteye.androidlivecourseapp.domain.splash

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.SplashRepository
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes

class SplashInteractor(private var repository : SplashRepository) {

    fun startupCheck(): Observable<ErrorsTypes> {
        Log.d("***", "SplashInteractor -> startupCheck")
        return repository.startupCheck()
    }

}
