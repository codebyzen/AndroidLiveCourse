package ru.iteye.androidlivecourseapp.data.firebase

import com.google.android.gms.tasks.Continuation
import com.google.firebase.functions.HttpsCallableResult
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.functions.FirebaseFunctionsException
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import kotlin.collections.HashMap


class AuthToken {

    private val TAG = "*** AuthToken"

    var fAuth: FirebaseAuth? = null


    private fun getToken(token: String): Task<String> {
        Log.d(TAG, " -> getToken: $token")
        val mFunctions: FirebaseFunctions = FirebaseFunctions.getInstance()

        val data = HashMap<String, Any>()
        data["uid"] = token

        return mFunctions
                .getHttpsCallable("getToken")
                .call(data)
                .continueWith(object : Continuation<HttpsCallableResult, String> {
                    override fun then(task: Task<HttpsCallableResult>): String {
                        val s = task.result.data as HashMap<*, *>
                        return s["customToken"].toString()
                    }
                })
    }

    fun signInWithToken(token: String, listener: TaskAuthFirebaseListener) {
        Log.d(TAG, " -> SignInWithToken")

        getToken(token)
                .addOnCompleteListener({
                    if (!it.isSuccessful) {
                        if (it.exception is FirebaseFunctionsException) {
                            val ffe = it.exception as FirebaseFunctionsException?
                            val code = ffe!!.code
                            val details = ffe.details
                            val type = ffe.message.toString()
                            Log.d(TAG, " -> SignInWithToken error type: $type")
                            listener.onError(FirebaseExpectionUtil("ERROR_FF_INTERNAL", ErrorsTypes.ERROR_FF_INTERNAL))
                        }
                        Log.d(TAG, " -> SignInWithToken -> Some error: " + it.exception?.message.toString())
                        listener.onError(FirebaseExpectionUtil(it.exception?.message.toString(), ErrorsTypes.ERROR_FF_INTERNAL))
                    } else {
                        fAuth = FirebaseAuth.getInstance()

                        Log.d(TAG, " -> SignInWithToken -> getToken:successes")
                        val task = fAuth?.signInWithCustomToken(it.result)

                        if (fAuth==null) {
                            Log.d(TAG, " -> SignInWithToken -> fAuth is NULL")
                        }

                        if (task == null) {
                            Log.d(TAG, " -> SignInWithToken -> task is NULL")
                            listener.onError(FirebaseExpectionUtil("USER_IS_NULL", ErrorsTypes.USER_IS_NULL))
                        }

                        task?.addOnCompleteListener({
                            if (it.isSuccessful) {
                                Log.d(TAG, " -> SignInWithToken -> success")
                                val user = fAuth?.currentUser
                                Log.d(TAG, user?.uid)
                                listener.onSuccess(it.result)
                            } else {
                                if (it.exception is FirebaseAuthException) {
                                    val fae = it.exception as FirebaseAuthException
                                    val code = fae.errorCode
                                    val details = fae.message.toString()
                                    Log.d(TAG, " -> signInWithCustomToken error : $code")
                                    Log.d(TAG, " -> signInWithCustomToken error : $details")
                                    listener.onError(FirebaseExpectionUtil(details, ErrorsTypes.ERROR_UNKNOWN_ERROR))
                                }
                                Log.w(TAG, " -> SignInWithToken -> failure", it.exception)
                            }
                        })
                    }
                })
    }



}