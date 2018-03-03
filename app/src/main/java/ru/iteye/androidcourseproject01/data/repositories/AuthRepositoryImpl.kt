package ru.iteye.androidcourseproject01.data.repositories

import android.util.Log
import ru.iteye.androidcourseproject01.data.database.firebase_auth.FirebaseAuth
import ru.iteye.androidcourseproject01.domain.global.repositories.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    private val firebase = FirebaseAuth()

    //TODO: перенести эту часть в FirebaseAuth
    override fun authByMail(email: String, password: String, afterRegistration: (Boolean?) -> Unit) {
        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")
        //TODO тут надо уже делать все через RX и использовать Firebase, твой код тот подойдет
        firebase.fAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Log.d("***", "AuthRepositoryImpl -> authByMail -> success")
                firebase.fUser = firebase.fAuth?.currentUser
                afterRegistration(false)
            } else {
                Log.w("***", "AuthRepositoryImpl -> authByMail -> failure", task.exception)
                //TODO потм убрать это в отдельную регистрацию
                registrationByEmail(email, password, afterRegistration)
            }
            //todo: надо отменить асинхронную операцию, когда вьюха уничтожается
        })
    }

    override fun checkAuth(afterCheck: (Boolean?) -> Unit){
        firebase.checkAuth(afterCheck)
    }

    //TODO: перенести эту часть в FirebaseAuth
    override fun registrationByEmail(email: String, password: String, afterRegistration: (Boolean?) -> Unit) {

        Log.d("***", "AuthEmailRepository -> signInEmail ($email/$password)")

        val registrationResults =  firebase.fAuth?.createUserWithEmailAndPassword(email, password)

        if (registrationResults==null) {
            Log.d("***", "AuthEmailRepository -> signInEmail -> registrationResults is NULL")
            this.authByMail(email, password, afterRegistration)
        }

        registrationResults?.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Log.d("***", "AuthEmailRepository -> signInEmail -> addOnCompleteListener -> task.isSuccessful")
                firebase.fUser = firebase.fAuth?.currentUser
                firebase.sendVerifyEmail()
                afterRegistration(true)
            }
        })
    }

}