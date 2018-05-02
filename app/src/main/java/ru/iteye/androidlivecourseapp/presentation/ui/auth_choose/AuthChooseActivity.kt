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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        authChoosePresenter.setView(this)

        val mProgressBar = this.findViewById<ProgressBar>(R.id.progressBar)
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE

        if (android.os.Build.VERSION.SDK_INT > 21 ) {
            mProgressBar.elevation = 2f
            mProgressBar.translationZ =  2f
            mProgressBarFrame.elevation = 2f
            mProgressBarFrame.translationZ = 2f
        }

        Log.d("***", "AuthChooseActivity -> ActivityAuthChoosePresenter")
        vkAccessTokenTracker.startTracking()
    }


    fun onBtnClickEmailType(view: View){
        Log.d("***", "AuthChooseActivity -> onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    fun onBtnClickVKType(view: View){
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.VISIBLE
        Log.d("***", "AuthChooseActivity -> onBtnClickVKType")
        authChoosePresenter.loginVK(this)
    }

    override fun onAuthVkFail(){
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE
        showMessage("Вы не разрешили доступ к профилю!",true)
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
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE
        val intent = Intent(this, FriendsListActivity::class.java)
        startActivity(intent)
    }

    fun onBtnClickFBType(view: View){}


    override fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }


}