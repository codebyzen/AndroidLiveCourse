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

        Log.d("CREATE", "SplashActivity")
        // проверяем Google Play Service и авторизацию
        splashPresenter.startupCheck()
    }

    // показываем сообщение о необходимости обновиться
    override fun googlePlayServiceOutdate(){
        showError(getString(R.string.error_google_play_version))
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

    override fun onNoInternet(){
        showError(getString(R.string.error_no_internet))
    }


}