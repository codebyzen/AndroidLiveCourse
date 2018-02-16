package ru.iteye.androidcourseproject01

import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.content.ContentValues.TAG
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.EmailAuthProvider


class AuthEmail: BaseActivity() {
    var ParentActivity: BaseActivity? = null                        //TODO: для всплывашки

    private val mAuth = FirebaseAuth.getInstance()
    private var mDatabase = FirebaseDatabase.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * тесты
         */
        setContentView(R.layout.login_screen_email)                 //TODO: для всплывашки
        ParentActivity = this.applicationContext as BaseActivity    //TODO: для всплывашки

        Log.d(TAG, "AuthEmail created!")
    }

    //TODO: для всплывашки
    override fun onResume() {
        super.onResume()
        ParentActivity?.setCurrentActivity(this)
    }
    //TODO: для всплывашки
    override fun onPause() {
        clearReferences()
        super.onPause()
    }
    //TODO: для всплывашки
    override fun onDestroy() {
        clearReferences()
        super.onDestroy()
    }
    //TODO: для всплывашки
    private fun clearReferences() {
        val currActivity = ParentActivity?.getCurrentActivity()
        if (this == currActivity)
            ParentActivity?.setCurrentActivity(null)
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun validateForm(emailField: EditText, passwordField: EditText): Boolean {

        if (TextUtils.isEmpty(emailField.text.toString()) || !isValidEmail(emailField.text)) {
            emailField.error = "Required correct email!"
            return false
        }

        if (TextUtils.isEmpty(passwordField.text.toString()) || passwordField.text.length<6) {
            passwordField.error = "Must be 6 symbols or more!"
            return false
        }

        return true
    }


    private fun sendVerifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Email sent.")
            }
        }
    }

    private fun checkForUserExist(email: String, password: String): FirebaseUser? {
        val user = FirebaseAuth.getInstance().currentUser

        val credential = EmailAuthProvider.getCredential(email, password)

        user?.reauthenticate(credential)?.addOnCompleteListener { Log.d(TAG, "User re-authenticated.") }

        return user
    }

    private fun authExistingUser(email: String, password: String): FirebaseUser? {

        var resultIs: FirebaseUser? = null

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(parent, { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        ParentActivity?.showToast("Authentication success!")
                        val user = mAuth.currentUser
                        resultIs = user // just return it to resultIs
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        ParentActivity?.showToast("Authentication failed!")
                        resultIs = null  // just return it to resultIs
                    }

                })
        return resultIs
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

        //val myRef = mDatabase.getReference("message")

        if (this.checkForUserExist(email, password)==null) {
            Log.d("***", "userExist PASS")
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( this, { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = mAuth.currentUser
                            //TODO: для всплывашки
                            // не появляется всплывашка =(
                            ParentActivity?.showToast("Successfully registered :)")
                            this.sendVerifyEmail(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            ParentActivity?.showToast("Authentication failed!")
                        }
                    })

        } else {
            authExistingUser(email, password)
        }



    }

}


