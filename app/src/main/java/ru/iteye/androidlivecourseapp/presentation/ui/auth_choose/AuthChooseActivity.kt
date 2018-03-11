package ru.iteye.androidlivecourseapp.presentation.ui.auth_choose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChoosePresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose.AuthChooseView
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity

class AuthChooseActivity: BaseActivity(), AuthChooseView {

    private val authChoosePresenter = AuthChoosePresenter()

    override fun showError(message: String) {
        Log.d("***", "AuthChooseActivity -> showError")
        showCustomAlert(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        authChoosePresenter.setView(this)
        Log.d("***", "AuthChooseActivity -> ActivityAuthChoosePresenter")
    }

    fun onBtnClickEmailType(view: View){
        Log.d("***", "AuthChooseActivity -> onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    override fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }

}