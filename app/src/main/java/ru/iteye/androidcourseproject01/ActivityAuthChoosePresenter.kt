package ru.iteye.androidcourseproject01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

/**
 * То, что называется ChoosePresenter не должна наследоваться от Activity
 * Это важное правило
 */
class ActivityAuthChoosePresenter: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        Log.d("CREATE", "ActivityAuthChoosePresenter")
    }

    fun onBtnClickEmailType(view: View){
        Log.d("CLICK", "onBtnClickEmailType")
        val intent = Intent(this, ActivityAuthEmailPresenter::class.java).apply {}
        startActivity(intent)
    }
}