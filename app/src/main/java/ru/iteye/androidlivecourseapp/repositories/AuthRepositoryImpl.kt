package ru.iteye.androidlivecourseapp.repositories

import android.util.Log
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository
import ru.iteye.androidlivecourseapp.data.firebase.AuthEmail
import ru.iteye.androidlivecourseapp.data.firebase.AuthToken


class AuthRepositoryImpl : AuthRepository {

    private val TAG = "*** AuthRepositoryImpl"

    private val firebaseMail = AuthEmail()
    private val firebaseToken = AuthToken()


    override fun authByMail(email: String, password: String): Observable<Boolean> {

        Log.d(TAG, " -> authByMail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d(TAG, " -> authByMail -> Observable.create ($email/$password)")
            Log.d(TAG, " -> authByMail -> thread name: " + Thread.currentThread().name)

            firebaseMail.authByMail(email, password, object : TaskAuthFirebaseListener {
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

    override fun authByToken(tokenVK: String): Observable<Boolean> {
        Log.d(TAG, " -> authByToken ($tokenVK)")

        return Observable.create<Boolean> { subscriber ->
            Log.d(TAG, " -> authByToken -> Observable.create")
            Log.d(TAG, " -> authByToken -> thread name: " + Thread.currentThread().name)

            firebaseToken.SignInWithToken(tokenVK, object : TaskAuthFirebaseListener {
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