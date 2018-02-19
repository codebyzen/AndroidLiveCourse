package ru.iteye.androidcourseproject01

import android.content.Intent
import android.os.Bundle
import android.util.Log

/**
 * Наследовать опять же от BaseActivity
 */
open class AppActivity : BaseActivity() {

    private val appPresenter = AppPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        // TODO это лишее
        setContext(this) // задаем контекст нашему BaseActivity

        Log.d("CREATE", "AppActivity")
        // проверяем Google Play Service и авторизацию
        appPresenter.startupCheckList()
    }

    // покажем активность с вариантами аутентификации
    fun startAuthChooseActivity(){
        val intent = Intent(this, AuthChooseActivity::class.java).apply {}
        startActivity(intent)
    }


    fun showError(msg: String){
        showToast(msg)
    }


}