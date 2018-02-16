package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText


class ActivityAuthEmailPresenter: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)
        Log.d("CREATE", "ActivityAuthEmailPresenter")
    }

    fun onBtnClickAuth(view: View){
        Log.d("CLICK","onBtnClickAuth click")

        val email = this.findViewById<EditText>(R.id.input_email)
        val password = this.findViewById<EditText>(R.id.input_password)

        Log.d("***", email.text.toString())
        Log.d("***", password.text.toString())

        val authEmail = AuthEmail()
        authEmail.signIn(email, password)


    }

}