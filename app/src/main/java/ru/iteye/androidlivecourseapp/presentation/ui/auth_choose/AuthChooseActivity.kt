package ru.iteye.androidlivecourseapp.presentation.ui.auth_choose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.vk.sdk.*
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChoosePresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChooseView
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity
import ru.iteye.androidlivecourseapp.presentation.ui.friends_list.FriendsListActivity


class AuthChooseActivity: BaseActivity(), AuthChooseView {

    private val authChoosePresenter = AuthChoosePresenter()
    private val TAG = "*** AuthChooseActivity "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        authChoosePresenter.setView(this)

        toggleProgressBar(false)

        if (android.os.Build.VERSION.SDK_INT > 21 ) {
            val mProgressBar = this.findViewById<ProgressBar>(R.id.progressBar)
            mProgressBar.apply {
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
        if (progressBarFrame.visibility == View.VISIBLE || letsShow==false) {
            Log.d(TAG, "-> toggleProgressBar -> hide ProgressBarFrame")
            R.id.progressBarFrame.apply {
                setVisible(false)
            }
        } else {
            Log.d(TAG, "-> toggleProgressBar -> show ProgressBarFrame")
            R.id.progressBarFrame.apply {
                setVisible(true)
            }
        }
    }


    fun onBtnClickEmailType(view: View){
        Log.d("***", "AuthChooseActivity -> onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    fun onBtnClickVKType(view: View){
        toggleProgressBar()
        Log.d("***", "AuthChooseActivity -> onBtnClickVKType")
        authChoosePresenter.loginVK(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("***", "AuthChooseActivity -> onActivityResult")
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
        val intent = Intent(this, FriendsListActivity::class.java)
        startActivity(intent)
    }

    fun onBtnClickFBType(view: View){}


    override fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }


}