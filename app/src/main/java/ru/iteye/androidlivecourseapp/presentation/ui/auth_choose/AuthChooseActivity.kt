package ru.iteye.androidlivecourseapp.presentation.ui.auth_choose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.vk.sdk.*
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChoosePresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChooseView
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity
import ru.iteye.androidlivecourseapp.presentation.ui.main_screen.MainScreenActivity


class AuthChooseActivity: BaseActivity(), AuthChooseView {

    private val authChoosePresenter = AuthChoosePresenter()
    private val TAG = "*** AuthChooseActivity "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        authChoosePresenter.setView(this)

        toggleProgressBar(false)

        if (android.os.Build.VERSION.SDK_INT > 21 ) {
            val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
            mProgressBarFrame.apply {
                elevation = 2f
                translationZ =  2f
                elevation = 2f
                translationZ = 2f
            }
        }

        Log.d("***", "AuthChooseActivity -> ActivityAuthChoosePresenter")
        vkAccessTokenTracker.startTracking()
    }

    override fun toggleProgressBar(letsShow: Boolean?){
        val progressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)

        //TODO: тут как-то надо сделать так, чтобы кнопки под этим элементом были ненажимабельные, пока сделаю так, но это не кошерно как-то
        if (letsShow==false) {
            Log.d(TAG, "-> toggleProgressBar -> hide ProgressBarFrame")
            progressBarFrame.visibility = View.GONE
            this.findViewById<Button>(R.id.button_login_email).isClickable = true
            this.findViewById<Button>(R.id.button_login_vk).isClickable = true
            this.findViewById<Button>(R.id.button_login_fb).isClickable = true
        } else {
            Log.d(TAG, "-> toggleProgressBar -> show ProgressBarFrame")
            progressBarFrame.visibility = View.VISIBLE
            this.findViewById<Button>(R.id.button_login_email).isClickable = false
            this.findViewById<Button>(R.id.button_login_vk).isClickable = false
            this.findViewById<Button>(R.id.button_login_fb).isClickable = false
        }
    }


    fun onBtnClickEmailType(view: View){
        Log.d("***", "AuthChooseActivity -> onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    fun onBtnClickVKType(view: View){
        toggleProgressBar(true)
        Log.d("***", "AuthChooseActivity -> onBtnClickVKType")
        authChoosePresenter.loginVK(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("***", "AuthChooseActivity -> onActivityResult")
        toggleProgressBar(true)
        Log.d(TAG, "AuthChooseActivity -> onActivityResult: requestCode = " + requestCode.toString())
        //TODO: тут надо как-то отслеживать от кого ответ чтобы делать signInVK или signInFB
        authChoosePresenter.signInVK(requestCode, resultCode, data)
    }

    var vkAccessTokenTracker: VKAccessTokenTracker = object : VKAccessTokenTracker() {
        override fun onVKAccessTokenChanged(oldToken: VKAccessToken?, newToken: VKAccessToken?) {
            Log.d("***", "AuthChooseActivity -> vkAccessTokenTracker")
            if (newToken == null) {
                // VKAccessToken is invalid
                Log.d("***", "AuthChooseActivity -> vkAccessTokenTracker -> VKAccessToken is invalid")
            }
        }
    }

    override fun superOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccessAuth() {
        Log.d("***", "AuthChooseActivity -> onSuccessAuth")
        toggleProgressBar(false)
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }

    override fun onAuthFail(error: String?) {

        var errorString: String = "ERROR_UNKNOWN_ERROR"

        if (error !== null) errorString = error

        var errorText: String = getString(R.string.ERROR_UNKNOWN_ERROR)

        if (errorString == "ERROR_VK_ACCESS_DENIED_BY_USER") {
            errorText = getString(R.string.ERROR_VK_ACCESS_DENIED_BY_USER)
        }

        toggleProgressBar(false)
        showError(errorText, {})
    }

    fun onBtnClickFBType(view: View){}


    override fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }


}