package ru.iteye.androidcourseproject01

import android.app.Activity
import android.content.Context
import android.widget.Toast
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AuthEmail(context: Context) {

    private val mContext: Context = context
    private val mContextActivity: Activity = mContext as Activity
    private val mAuth = FirebaseAuth.getInstance()
    private var mDatabase = FirebaseDatabase.getInstance()

    private fun validateForm(emailField: EditText, passwordField: EditText): Boolean {
        var valid = true

        if (TextUtils.isEmpty(emailField.text.toString())) {
            emailField.error = "Required."
            valid = false
        }

        if (TextUtils.isEmpty(passwordField.text.toString())) {
            passwordField.error = "Required."
            valid = false
        }

        return valid
    }


    /**
     * Пытаемся войти с предоставленными данными
     * Если не получается (нет пользователя) то регистрируем нового
     */
    //TODO: пока не доделал это.
    fun signIn(emailField: EditText, passwordField: EditText) {
        Log.d("***", "signIn:" + emailField.text.toString())
        if (!validateForm(emailField, passwordField)) {
            return
        }

        val email = emailField.text.toString()
        val password = passwordField.text.toString()

        val myRef = mDatabase.getReference("message")

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(mContextActivity, { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                val uid = user!!.uid
                myRef.child(uid).child("Name").setValue(user.displayName)
                //startActivity(Intent(this, Timeline::class.java))
                Toast.makeText(mContext, "Successfully registered :)", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(mContext, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
            }
        })



    }

}


