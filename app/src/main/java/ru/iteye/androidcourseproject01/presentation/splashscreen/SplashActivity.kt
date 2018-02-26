package ru.iteye.androidcourseproject01.presentation.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.authchoose.AuthChooseActivity
import ru.iteye.androidcourseproject01.presentation.friendslist.FriendsListActivity
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.presentation.view.splash.SplashView

class SplashActivity : BaseActivity(), SplashView {

    private val splashPresenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        splashPresenter.setView(this)

        Log.d("CREATE", "SplashActivity")
        // проверяем Google Play Service и авторизацию
        splashPresenter.startupCheckList()
    }

    // покажем активность с вариантами аутентификации
    override fun startAuthChooseActivity(){
        val intent = Intent(this, AuthChooseActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun startFriendsListActivity(){
        val intent = Intent(this, FriendsListActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun getActivityContext(): Context {
        return this
    }

    override fun showCustomAlertDialog(customMessage: String) {
        Log.d("***", "SplashActivity -> showCustomAlertDialog")
        showCustomAlert(customMessage)
    }

}