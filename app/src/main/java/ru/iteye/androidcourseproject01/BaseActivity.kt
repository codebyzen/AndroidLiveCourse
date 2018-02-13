package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.util.Log

//todo: Я не понимаю зачем нам это
open class BaseActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }
}


