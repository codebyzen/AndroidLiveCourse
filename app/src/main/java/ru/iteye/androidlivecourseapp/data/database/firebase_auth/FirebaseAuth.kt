package ru.iteye.androidlivecourseapp.data.database.firebase_auth

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.Tasks
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeoutException
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import android.widget.Toast
import jdk.nashorn.internal.runtime.ECMAException.getException




open class FirebaseAuth {
    var fAuth: FirebaseAuth? = null
    var fUser: FirebaseUser? = null


    init {
        fAuth = FirebaseAuth.getInstance()
    }

    /**
     * Высылаем пользователю email с подтверждением регистрации
     */
    fun sendVerifyEmail(){
        Log.d("***", "FirebaseAuth -> sendVerifyEmail")
        if (fUser != null) {
            fUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Email sent.")
                }
            }
        }
    }

    //TODO: используем пока это для теста
    fun authCheckTest(callback: (results: ErrorsTypes) -> Unit) {
        Log.d("***", "FirebaseAuth -> authCheckTest")
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            callback(ErrorsTypes.ERROR_USER_NOT_FOUND)
        } else {
           currentUser.reload().addOnCompleteListener { task ->
               if (task.isSuccessful) {
                    callback(ErrorsTypes.ALLOK)
                } else {
                   //TODO: тут как-то можно посмотреть ошибку, пока не разобрался.
                    Log.d("***", "FirebaseAuth -> authCheckTest -> task: " + task.exception?.cause.toString())
                    Log.d("***", "FirebaseAuth -> authCheckTest -> task: " + task.exception?.message.toString())
                    callback(ErrorsTypes.USERNOTAUTH)
                }
            }
        }
    }


    /**
     * Проверяем авторизован ли уже пользователь и выполняем замыкание
     */
    fun checkAuth(): ErrorsTypes {
        Log.d("***", "FirebaseAuth -> checkAuth")

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            Log.d("***", "FirebaseAuth -> checkAuth -> currentUser is NULL")
            return ErrorsTypes.USERISNULL
        }


        val emailVerified = currentUser.isEmailVerified
        if (!emailVerified) {
            return ErrorsTypes.EMAILNOTVIRIFIED
        }

        Log.d("***", "FirebaseAuth -> checkAuth -> currentUser = "+currentUser.toString())


        val task = currentUser.reload()

        Log.d("***", "FirebaseAuth -> checkAuth -> task = "+task.toString())


        return try {
            val authResult = Tasks.await(task)
            this.fUser = fAuth?.currentUser
            Log.d("***", "FirebaseAuth -> checkAuth -> fUser: " + this.fUser.toString())
            ErrorsTypes.ALLOK
        } catch (e: ExecutionException) {
            Log.d("***", "FirebaseAuth -> checkAuth -> ExecutionException: " + e.message.toString())
            Log.d("***", "FirebaseAuth -> checkAuth -> ExecutionException: " + e.localizedMessage.toString())
            ErrorsTypes.EXECUTIONEXCEPTION
        } catch (e: InterruptedException) {
            Log.d("***", "FirebaseAuth -> checkAuth -> InterruptedException: " + e.message.toString())
            ErrorsTypes.INTERUPTEDEXCEPTION
        } catch (e: TimeoutException) {
            Log.d("***", "FirebaseAuth -> checkAuth -> TimeoutException: " + e.message.toString())
            ErrorsTypes.TIMEOUTECXEPTION
        }


    }

    fun authByMail(email: String, password: String): ErrorsTypes {

        Log.d("***", "AuthRepositoryImpl -> authByMail ($email/$password)")

        val task = fAuth?.signInWithEmailAndPassword(email, password)

        if (task == null) {
            Log.d("***", "FirebaseAuth -> authByMail -> currentUser is NULL")
            return ErrorsTypes.AUTHERROR
        }

        return try {
            val authResult = Tasks.await(task)
            //Log.d("***", "FirebaseAuth -> authByMail -> ExecutionException: " + authResult.toString())
            ErrorsTypes.ALLOK
        } catch (e: ExecutionException) {
            Log.d("***", "FirebaseAuth -> authByMail -> ExecutionException: " + e.message.toString())
            //TODO: как-то надо отлавливать сообщения и реагировать на них, но скорее всего пользователь не найден
            ErrorsTypes.USERNOTAUTH
        } catch (e: InterruptedException) {
            Log.d("***", "FirebaseAuth -> authByMail -> InterruptedException: " + e.message.toString())
            ErrorsTypes.INTERUPTEDEXCEPTION
        } catch (e: TimeoutException) {
            Log.d("***", "FirebaseAuth -> authByMail -> TimeoutException: " + e.message.toString())
            ErrorsTypes.TIMEOUTECXEPTION
        }


    }


    fun regByMail(email: String, password: String): ErrorsTypes {
        Log.d("***", "AuthRepositoryImpl -> regByMail ($email/$password)")

        val task = fAuth?.createUserWithEmailAndPassword(email, password)

        if (task == null) {
            Log.d("***", "FirebaseAuth -> regByMail -> currentUser is NULL")
            return ErrorsTypes.AUTHERROR
        }

        return try {
            val authResult = Tasks.await(task)
            //Log.d("***", "FirebaseAuth -> regByMail -> ExecutionException: " + authResult.toString())
            sendVerifyEmail()
            ErrorsTypes.ALLOK
        } catch (e: ExecutionException) {
            Log.d("***", "FirebaseAuth -> regByMail -> ExecutionException: " + e.message.toString())
            //TODO: как-то надо отлавливать сообщения и реагировать на них, но скорее всего пользователь не найден
            ErrorsTypes.USERNOTAUTH
        } catch (e: InterruptedException) {
            Log.d("***", "FirebaseAuth -> regByMail -> InterruptedException: " + e.message.toString())
            ErrorsTypes.INTERUPTEDEXCEPTION
        } catch (e: TimeoutException) {
            Log.d("***", "FirebaseAuth -> regByMail -> TimeoutException: " + e.message.toString())
            ErrorsTypes.TIMEOUTECXEPTION
        }
    }
}