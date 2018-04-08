package ru.iteye.androidlivecourseapp.repositories

import android.util.Log
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.repositories.RegRepository
import ru.iteye.androidlivecourseapp.repositories.authemail.RegEmailRepository


class RegRepositoryImpl: RegRepository {

    private val firebase = RegEmailRepository()


    override fun registrationByEmail(email: String, password: String) : Observable<Boolean> {

        Log.d("***", "RegRepositoryImpl -> registrationByEmail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "RegRepositoryImpl -> registrationByEmail -> Observable.create ($email/$password)")
            Log.d("***", "RegRepositoryImpl -> registrationByEmail -> thread name: " + Thread.currentThread().name)

            firebase.regByMail(email, password, object : TaskAuthFirebaseListener {
                override fun onSuccess(result: AuthResult) {
                    subscriber.onNext(true)
                    subscriber.onComplete()
                }
                override fun onComplete() {
                    subscriber.onNext(true)
                    subscriber.onComplete()
                }
                override fun onError(exception: Exception?) {
                    subscriber.onError(exception)
                }
            })
        }
    }

}