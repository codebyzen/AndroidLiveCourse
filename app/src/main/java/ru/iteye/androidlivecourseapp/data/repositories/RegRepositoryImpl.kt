package ru.iteye.androidlivecourseapp.data.repositories

import android.util.Log
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.data.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.repositories.RegRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes


class RegRepositoryImpl: RegRepository {

    private val firebase = FirebaseAuth()


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