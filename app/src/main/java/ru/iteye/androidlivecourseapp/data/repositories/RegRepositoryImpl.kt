package ru.iteye.androidlivecourseapp.data.repositories

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.domain.global.repositories.RegRepository

class RegRepositoryImpl: RegRepository {

    private val firebase = FirebaseAuth()

    override fun registrationByEmail(email: String, password: String) : Observable<Boolean?> {

        Log.d("***", "RegRepositoryImpl -> registrationByEmail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "RegRepositoryImpl -> registrationByEmail -> Observable.create ($email/$password)")
            Log.d("***", "RegRepositoryImpl -> registrationByEmail -> thread name: " + Thread.currentThread().name)

            try {
                val registrationResults = firebase.fAuth?.createUserWithEmailAndPassword(email, password)

                if (registrationResults == null) {
                    Log.d("***", "RegRepositoryImpl -> registrationByEmail -> registrationResults is NULL")
                }

                registrationResults?.addOnCompleteListener({ task ->
                    if (task.isSuccessful) {
                        Log.d("***", "RegRepositoryImpl -> registrationByEmail -> addOnCompleteListener -> task.isSuccessful")
                        firebase.fUser = firebase.fAuth?.currentUser
                        firebase.sendVerifyEmail()
                        subscriber.onNext(true)
                        subscriber.onComplete()
                    } else {
                        Log.w("***", "RegRepositoryImpl -> registrationByEmail -> failure", task.exception)
                        subscriber.onNext(false)
                        subscriber.onComplete()
                    }
                })
            } catch (e: Exception) {
                subscriber.onNext(null)
                subscriber.onError(e)
            }
        }

    }

}