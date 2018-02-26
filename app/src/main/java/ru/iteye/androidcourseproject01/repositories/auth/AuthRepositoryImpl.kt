package ru.iteye.androidcourseproject01.repositories.auth

import android.util.Log
import ru.iteye.androidcourseproject01.data.FirebaseImpl

class AuthRepositoryImpl : AuthRepository {

    private val firebase = FirebaseImpl()

    //TODO: перенести эту часть в FirebaseImpl
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
                // пробуем зарегистрировать пользователя если такого нет
                //TODO потм убрать это в отдельную регистрацию
                registrationByEmail(email, password, afterRegistration)
            }
            //todo: надо отменить асинхронную операцию, когда вьюха уничтожается
        })
    }

    override fun checkAuth(afterCheck: (Boolean?) -> Unit){
        firebase.checkAuth(afterCheck)
    }

    //TODO: перенести эту часть в FirebaseImpl
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