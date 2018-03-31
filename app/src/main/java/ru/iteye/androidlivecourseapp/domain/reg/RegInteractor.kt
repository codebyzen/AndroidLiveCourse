package ru.iteye.androidlivecourseapp.domain.reg

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.domain.global.repositories.RegRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

class RegInteractor(private var repository : RegRepository) {

    fun regByMail(email : String, password : String): Observable<Boolean> {
        Log.d("***", "RegInteractor -> regByMail")
        return repository.registrationByEmail(email, password)
    }

}