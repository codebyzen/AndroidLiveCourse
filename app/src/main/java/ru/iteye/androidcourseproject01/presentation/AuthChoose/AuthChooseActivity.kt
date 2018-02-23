package ru.iteye.androidcourseproject01.presentation.AuthChoose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.AuthEmail.AuthEmailActivity

class AuthChooseActivity: BaseActivity() {

    private val authChoosePresenter = AuthChoosePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        Log.d("CREATE", "ActivityAuthChoosePresenter")
    }

    /**
     * Если кликнули на авторизацию по email
     */
    fun onBtnClickEmailType(view: View){
        Log.d("CLICK", "onBtnClickEmailType")
        authChoosePresenter.startAuthEmailActivity()
    }

    fun startAuthEmailActivity(){
        val intent = Intent(this, AuthEmailActivity::class.java)
        startActivity(intent)
    }

}