package ru.iteye.androidlivecourseapp.data.repositories

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidlivecourseapp.domain.global.repositories.AuthRepository


class AuthRepositoryImpl : AuthRepository {

    private val firebase = FirebaseAuth()


    override fun authByMail(email: String, password: String): Observable<Boolean?> {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        return Observable.create<Boolean> { subscriber ->
            Log.d("***", "AuthRepositoryImpl -> authByMail -> Observable.create ($email/$password)")
            Log.d("***", "AuthRepositoryImpl -> authByMail -> thread name: " + Thread.currentThread().name)

            try {
                firebase.fAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
                    if (task.isSuccessful) {
                        Log.d("***", "AuthRepositoryImpl -> authByMail -> success")
                        firebase.fUser = firebase.fAuth?.currentUser
                        //afterRegistration(false)
                        subscriber.onNext(true)
                        subscriber.onComplete()
                    } else {
                        Log.w("***", "AuthRepositoryImpl -> authByMail -> failure", task.exception)
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

    override fun checkAuth(afterCheck: (Boolean?) -> Unit){
        firebase.checkAuth(afterCheck)
    }





}