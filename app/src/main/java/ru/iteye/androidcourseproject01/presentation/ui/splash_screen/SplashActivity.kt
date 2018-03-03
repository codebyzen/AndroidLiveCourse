package ru.iteye.androidcourseproject01.presentation.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.ui.auth_choose.AuthChooseActivity
import ru.iteye.androidcourseproject01.presentation.ui.friends_list.FriendsListActivity
import ru.iteye.androidcourseproject01.presentation.ui.global.BaseActivity
import ru.iteye.androidcourseproject01.presentation.mvp.splash_screen.SplashPresenter
import ru.iteye.androidcourseproject01.presentation.mvp.splash_screen.SplashView

class SplashActivity : BaseActivity(), SplashView {

    private val splashPresenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        splashPresenter.setView(this)
        splashPresenter.setContext(applicationContext)

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


    override fun showCustomAlertDialog(customMessage: String) {
        Log.d("***", "SplashActivity -> showCustomAlertDialog")
        showCustomAlert(customMessage)
    }

}