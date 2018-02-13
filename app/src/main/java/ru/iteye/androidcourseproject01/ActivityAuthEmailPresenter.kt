package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener
import android.widget.EditText


class ActivityAuthEmailPresenter: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)
        Log.d("CREATE", "ActivityAuthEmailPresenter")
    }

    fun onBtnClickAuth(view: View){
        Log.d("CLICK","onBtnClickAuth click")
        val email = view.findViewById<EditText>(R.id.input_email)
        val password = view.findViewById<EditText>(R.id.input_password)

        val authEmail = AuthEmail(this)
        authEmail.signIn(this.findViewById<EditText>(R.id.input_email), this.findViewById<EditText>(R.id.input_password))


    }

}