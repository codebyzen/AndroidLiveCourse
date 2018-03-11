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



    override fun registrationByEmail(email: String, password: String) {
        Log.d("***", "AuthEmailRepository -> signInEmail ($email/$password)")

        val registrationResults =  firebase.fAuth?.createUserWithEmailAndPassword(email, password)

        if (registrationResults==null) {
            Log.d("***", "AuthEmailRepository -> signInEmail -> registrationResults is NULL")
            this.authByMail(email, password)
        }

        registrationResults?.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Log.d("***", "AuthEmailRepository -> signInEmail -> addOnCompleteListener -> task.isSuccessful")
                firebase.fUser = firebase.fAuth?.currentUser
                firebase.sendVerifyEmail()
            }
        })
    }

}