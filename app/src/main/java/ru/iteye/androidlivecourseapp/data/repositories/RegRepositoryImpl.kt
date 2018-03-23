package ru.iteye.androidlivecourseapp.data.repositories

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.domain.global.repositories.RegRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes

class RegRepositoryImpl: RegRepository {

    private val firebase = FirebaseAuth()

    override fun registrationByEmail(email: String, password: String) : Observable<ErrorsTypes> {

        Log.d("***", "RegRepositoryImpl -> registrationByEmail ($email/$password)")


        return Observable.create<ErrorsTypes> { subscriber ->
            Log.d("***", "AuthRepositoryImpl -> authByMail -> Observable.create ($email/$password)")
            Log.d("***", "AuthRepositoryImpl -> authByMail -> thread name: " + Thread.currentThread().name)

            try {
                val authResult = firebase.regByMail(email,password)
                subscriber.onNext(authResult)
                subscriber.onComplete()
            } catch (e: Exception) {
                subscriber.onNext(ErrorsTypes.EXECUTIONEXCEPTION)
                subscriber.onError(e)
            }


        }

    }

}