package ru.iteye.androidcourseproject01.presentation.authchoose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.view.authchoose.AuthChooseView
import ru.iteye.androidcourseproject01.presentation.authemail.AuthEmailActivity

class AuthChooseActivity: BaseActivity(), AuthChooseView {

    private val authChoosePresenter = AuthChoosePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        authChoosePresenter.setView(this)
        Log.d("***", "AuthChooseActivity -> ActivityAuthChoosePresenter")
    }

    /**
     * Если кликнули на авторизацию по email
     */
    fun onBtnClickEmailType(view: View){
        Log.d("***", "AuthChooseActivity -> onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    override fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }

}