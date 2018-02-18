package ru.iteye.androidcourseproject01

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




/**
 * Класс для авторизации (тут будет логика авторизации)
 */
class AuthEmailRepository {

    var fAuth: FirebaseAuth? = null // класс авторизации firebase
    var fUser: FirebaseUser? = null // класс пользователя firebase

    /**
     * Возвращаем тру или фолс в зависимости от того авторизован пользователь или нет
     */
    fun checkAuth(): FirebaseUser? {
        Log.d("***", "AuthCheck checkAuth")
        FirebaseAuth.getInstance().currentUser?.reload() // перегружаем чтобы из сессии не тянул
        this.fAuth = FirebaseAuth.getInstance()
        this.fUser = fAuth?.currentUser
        var resultIs: FirebaseUser? = null
        if (this.fUser==null) {
            resultIs = null
        } else {
            resultIs = this.fUser
        }
        return resultIs
    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, "Email sent.")
            }
        }
    }


    /**
     * Авторизуем пользователя по паре email, password
     */
    private fun authExistingUser(email: String, password: String) {
        fAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, "signInWithEmail:success")
                fUser = fAuth?.currentUser
            } else {
                Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
            }
            //todo: перенести колбек код в презентер
            //todo: надо отменить асинхронную операцию, когда вьюха уничтожается
        })
    }

    fun signInEmail(email: String, password: String) {

        if (checkAuth()==null) {
            Log.d("***", "userExist PASS")

            fAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener({ task ->
                if (task.isSuccessful) {
                    Log.d("***", "createUserWithEmail:success")
                    fUser = fAuth?.currentUser
                    sendVerifyEmail(fUser)
                } else {
                    Log.w("***", "createUserWithEmail:failure", task.exception)
                }
                //todo: перенести колбек код в презентер
                //todo: надо отменить асинхронную операцию, когда вьюха уничтожается

            })

        } else {
            authExistingUser(email, password)
        }

    }

}