package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


open class AppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

}