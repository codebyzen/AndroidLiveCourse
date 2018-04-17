package ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.VKExceptionUtil


class AuthChoosePresenter: BasePresenter<AuthChooseView>() {

    fun startAuthEmailActivity() {
        getView()?.startAuthEmailActivity()
    }

    fun signInVK(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.d("***", "AuthVKPresenter -> signIn")

        if(resultCode != AppCompatActivity.RESULT_CANCELED && data != null) {
            if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                        override fun onResult(res: VKAccessToken) {
                            // Пользователь успешно авторизовался
                            Log.d("***", "AuthVKPresenter -> onActivityResult -> VK auth success")
                            onUserAuthenticated()
                        }

                        override fun onError(error: VKError) {
                            // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                            Log.d("***", "AuthVKPresenter -> onActivityResult -> VK auth failed")
                            Log.d("***", "AuthVKPresenter -> onActivityResult -> VK auth failed -> code: " + error.errorCode.toString())
                            Log.d("***", "AuthVKPresenter -> onActivityResult -> VK auth failed -> message: " + error.errorMessage.toString())
                            afterAuthentificationError(VKExceptionUtil(error.errorMessage, ErrorsTypes.ERROR_UNKNOWN_ERROR))
                        }
                    }
                    )
            ) {
                getView()?.superOnActivityResult(requestCode, resultCode, data)
            }
        } else {
            Log.d("***", "AuthVKPresenter -> onActivityResult -> data is NULL!")
            if (VKSdk.isLoggedIn()) {
                onUserAuthenticated()
            }
        }

    }

    private fun afterAuthentificationError(error: Throwable) {
        Log.d("***", "AuthVKPresenter -> afterAuthentificationError -> error: " + error.message.toString())
        getView()?.showError(error.message!!, {})
    }

    private fun onUserAuthenticated() {
        Log.d("***", "AuthVKPresenter -> onUserAuthenticated -> user authenticated")
        getView()?.onSuccessAuth()
    }


}