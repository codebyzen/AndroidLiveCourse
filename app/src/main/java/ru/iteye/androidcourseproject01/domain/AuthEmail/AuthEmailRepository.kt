package ru.iteye.androidcourseproject01.domain.AuthEmail

import android.content.ContentValues
import android.util.Log
import ru.iteye.androidcourseproject01.data.FirebaseImpl


/**
 * Класс для авторизации (тут будет логика авторизации)
 */
class AuthEmailRepository: FirebaseImpl() {

    /**
     * Авторизуем пользователя по паре email, password
     */
    private fun authExistingUser(email: String, password: String, afterRegistration: (Boolean?) -> Unit) {
        fAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, "signInWithEmail:success")
                fUser = fAuth?.currentUser
                afterRegistration(false)
            } else {
                Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                afterRegistration(null)
            }
            //todo: надо отменить асинхронную операцию, когда вьюха уничтожается
        })
    }

    fun signInEmail(email: String, password: String, afterRegistration: (Boolean?) -> Unit) {

            fAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
                if (task.isSuccessful) {
                    Log.d("***", "createUserWithEmail:success")
                    fUser = fAuth?.currentUser
                    sendVerifyEmail()
                    afterRegistration(true)
                } else {
                    Log.w("***", "createUserWithEmail:failure", task.exception)
                    authExistingUser(email, password, afterRegistration)
                }
                //todo: надо отменить асинхронную операцию, когда вьюха уничтожается
            })
    }

}