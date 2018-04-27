package ru.iteye.androidlivecourseapp.repositories

import android.util.Log
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository
import ru.iteye.androidlivecourseapp.data.firebase.AuthEmail


class AuthRepositoryImpl : AuthRepository {

    private val firebase = AuthEmail()


    override fun authByMail(email: String, password: String): Observable<Boolean> {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "AuthRepositoryImpl -> authByMail -> Observable.create ($email/$password)")
            Log.d("***", "AuthRepositoryImpl -> authByMail -> thread name: " + Thread.currentThread().name)

            firebase.authByMail(email, password, object : TaskAuthFirebaseListener {
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