package ru.iteye.androidlivecourseapp.presentation.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.ui.auth_choose.AuthChooseActivity
import ru.iteye.androidlivecourseapp.presentation.ui.friends_list.FriendsListActivity
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen.SplashPresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen.SplashView

class SplashActivity : BaseActivity(), SplashView {

    private val splashPresenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        splashPresenter.setView(this)
//        splashPresenter.setContext(ru.iteye.androidcourseproject01.domain.global.models.Application.appContext)

        Log.d("CREATE", "SplashActivity")
        // проверяем Google Play Service и авторизацию
        splashPresenter.startupCheckList()
    }


    // покажем активность с вариантами аутентификации
    override fun startAuthChooseActivity() {
        val intent = Intent(this, AuthChooseActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun startFriendsListActivity() {
        val intent = Intent(this, FriendsListActivity::class.java).apply {}
        startActivity(intent)
    }


}