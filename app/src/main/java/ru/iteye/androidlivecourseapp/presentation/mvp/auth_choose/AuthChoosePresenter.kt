package ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.domain.auth.AuthInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.repositories.AuthRepositoryImpl
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.VKExceptionUtil
import java.lang.Exception
import java.security.NoSuchAlgorithmException


class AuthChoosePresenter: BasePresenter<AuthChooseView>() {

    private val TAG = "*** AuthChoosePresenter"
    private var interactor = AuthInteractor(AuthRepositoryImpl())

    fun md5(s: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices)
                hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * Функция вызывает функцию перехода к новому лейауту у родительской Activity
     */
    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

    /**
     * Функция для логина через VK
     */
    fun loginVK(pAct: Activity) {
        VKSdk.login(pAct, VKScope.EMAIL, VKScope.FRIENDS)
    }

    /**
     * Функция вызывается из родительской Activity функции onActivityResult
     */
    fun signInVK(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.d(TAG, " -> signInVK")

        val isTokenExist = VKAccessToken.currentToken()
        if (isTokenExist==null) {
            val errorVKToken = VKExceptionUtil("ERROR_VK_ACCESS_DENIED_BY_USER", ErrorsTypes.ERROR_VK_ACCESS_DENIED_BY_USER)
            afterAuthentificationError(errorVKToken) //ERROR_VK_ACCESS_DENIED_BY_USER
            return
        }

        val tokenVK: String? = VKAccessToken.currentToken().accessToken
        Log.d(TAG, " -> signInVK -> tokenVK: " + tokenVK.toString())


        if (tokenVK == null) {
            val errorVKToken = VKExceptionUtil(  "ERROR_VK_ACCESS_DENIED_BY_USER", ErrorsTypes.ERROR_VK_ACCESS_DENIED_BY_USER)
            afterAuthentificationError(errorVKToken)
            return
        }

        val tokenForFirebase: String = md5("VK_WSWM" + tokenVK + "VK_WSWM")

        val callback = object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                disposables.add(
                        interactor.authByToken(tokenForFirebase)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ isAuthSuccess ->
                                    Log.d(TAG, " -> signInVK -> thread name: " + Thread.currentThread().name)
                                    Log.d(TAG, " -> signInVK -> observableAuthResult: " + isAuthSuccess.toString())
                                    onUserAuthenticated()
                                }, { error ->
                                    error.printStackTrace()
                                    Log.d(TAG, " -> signInVK -> error " + error.message.toString())
                                    if (error.message.toString() == "ERROR_EMAIL_ALREADY_IN_USE") {
                                        onUserAlreadyRegistered()
                                    } else {
                                        afterAuthentificationError(error)
                                    }
                                })
                )

            }
            override fun onError(error: VKError) {
                Log.d(TAG," -> signInVK -> onError")
                getView()?.toggleProgressBar(false)
                getView()?.showError("Вы не разрешили доступ к профилю!",{})
            }
        }
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            getView()?.superOnActivityResult(requestCode, resultCode, data)
        }
    }

    private fun onUserAlreadyRegistered(){
        val error = Exception("Some error!")
        afterAuthentificationError(error)
    }

    /**
     * Функция показывает ошибку и вызывается при ошибке в signInVK
     */
    private fun afterAuthentificationError(error: Throwable) {
        Log.d(TAG, " -> afterAuthentificationError -> error: " + error.message.toString())
        getView()?.onAuthFail(error.message)
    }


    /**
     * Функция вызывает из родительской активити функцию onSuccessAuth
     * при успешном результате в signInVK
     */
    private fun onUserAuthenticated() {
        Log.d(TAG, " -> onUserAuthenticated -> user authenticated")
        getView()?.onSuccessAuth()
    }


}