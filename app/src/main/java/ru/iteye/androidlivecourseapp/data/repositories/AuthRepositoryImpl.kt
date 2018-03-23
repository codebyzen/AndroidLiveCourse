package ru.iteye.androidlivecourseapp.data.repositories

import android.util.Log
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.data.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes


class AuthRepositoryImpl : AuthRepository {

    private val firebase = FirebaseAuth()


    override fun authByMail(email: String, password: String): Observable<Boolean> {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "AuthRepositoryImpl -> authByMail -> Observable.create ($email/$password)")
            Log.d("***", "AuthRepositoryImpl -> authByMail -> thread name: " + Thread.currentThread().name)

            firebase.authByMail(email, password, object : TaskAuthFirebaseListener {
                override fun onSuccess(result: AuthResult) {
//                        subscriber.onNext(result)
                    subscriber.onComplete()
                }

                override fun onError(exception: Exception?) {
                    subscriber.onError(exception)
                }
            })
        }
    }

    override fun checkAuth(): ErrorsTypes {
        Log.d("***", "AuthRepositoryImpl -> checkAuth")
        return firebase.checkAuth()
    }

    override fun checkAuthTest(callback: (results: ErrorsTypes) -> Unit) {
        Log.d("***", "AuthRepositoryImpl -> checkAuth")
        firebase.authCheckTest(callback)
    }


}