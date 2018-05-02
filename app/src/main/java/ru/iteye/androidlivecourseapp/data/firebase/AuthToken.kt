package ru.iteye.androidlivecourseapp.data.firebase

import com.google.android.gms.tasks.Continuation
import com.google.firebase.functions.HttpsCallableResult
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.functions.FirebaseFunctionsException
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskAuthFirebaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.HashMap


class AuthToken {

    private val TAG = "*** AuthToken"

    var fAuth: FirebaseAuth? = null

    /*
    * Оставлю пока для тестов
    * */
    private fun sendPost(tokenVK: String): String {

        Log.d(TAG, " -> sendPost")

        val urlParameters = "{\"uid\": \"$tokenVK\"}"
        val postData = urlParameters.toByteArray(StandardCharsets.UTF_8)
        val postDataLength = postData.size
        val request = "https://us-central1-androidcourseproject-26568.cloudfunctions.net/getToken"
        val url = URL(request)
        val conn = url.openConnection() as HttpURLConnection
        conn.doOutput = true
        conn.instanceFollowRedirects = false
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("charset", "utf-8")
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength))
        conn.useCaches = false
        Log.d(TAG, " -> sendPost -> Prepare to send POST")
        DataOutputStream(conn.outputStream).use({ wr -> wr.write(postData) })
        Log.d(TAG, " -> sendPost -> Send POST")
        val buff = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))
        Log.d(TAG, " -> sendPost -> Read answer")
        var c: Int = buff.read()
        var s: String = ""
        while (c >= 0) {
            c = buff.read()
            s += c.toChar()
        }
        Log.d(TAG, "Token from Functions$s")
        return s
    }

    private fun getToken(tokenVK: String): Task<String> {
        Log.d(TAG, " -> getToken: $tokenVK")
        val mFunctions: FirebaseFunctions = FirebaseFunctions.getInstance()
        Log.d(TAG, " -> getToken: 2")

        val data = HashMap<String, Any>()
        data["uid"] = tokenVK

        Log.d(TAG, " -> getToken: 3")

        return mFunctions
                .getHttpsCallable("getToken")
                .call(data)
                .continueWith(object : Continuation<HttpsCallableResult, String> {
                    override fun then(task: Task<HttpsCallableResult>): String {
                        Log.d(TAG, " -> getToken -> hashed token from FireBaseFunctions: " + task.result.data.toString())
                        return task.result.data as String
                    }
                })
    }

    fun SignInWithToken(tokenVK: String, listener: TaskAuthFirebaseListener) {
        Log.d(TAG, " -> SignInWithToken")
        //val token = sendPost(tokenVK)


        getToken(tokenVK)
                .addOnCompleteListener({
                    if (!it.isSuccessful) {
                        val e = it.exception
                        if (e is FirebaseFunctionsException) {
                            val ffe = e as FirebaseFunctionsException?
                            val code = ffe!!.code
                            val details = ffe.details
                            val type = ffe.message.toString()
                            Log.d(TAG, " -> SignInWithToken error type: $type")
                            listener.onError(FirebaseExpectionUtil("ERROR_FF_INTERNAL", ErrorsTypes.ERROR_FF_INTERNAL))
                        }
                        Log.d(TAG, " -> SignInWithToken -> Some error: " + it.exception?.message.toString())
                    } else {
                        Log.d(TAG, " -> SignInWithToken -> task:successes")
                        val task = fAuth?.signInWithCustomToken(it.result)

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
                                Log.w(TAG, " -> SignInWithToken -> failure", task.exception)
                                listener.onError(FirebaseExpectionUtil("ERROR_UNKNOWN_ERROR", ErrorsTypes.ERROR_UNKNOWN_ERROR))
                            }
                        })
                    }
                })
    }



}