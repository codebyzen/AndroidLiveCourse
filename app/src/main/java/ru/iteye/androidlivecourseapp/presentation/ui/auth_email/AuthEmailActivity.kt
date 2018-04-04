package ru.iteye.androidlivecourseapp.presentation.ui.auth_email

import android.util.Log
import android.widget.EditText
import android.content.Intent
import android.os.Bundle
import android.view.View
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailPresenter
import ru.iteye.androidlivecourseapp.presentation.ui.friends_list.FriendsListActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailView
import ru.iteye.androidlivecourseapp.utils.ValidateUtils
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes


class AuthEmailActivity : BaseActivity(), AuthEmailView {


    private val authEmailPresenter = AuthEmailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)

        authEmailPresenter.setView(this)
        Log.d("***", "AuthEmailActivity created!")
    }

    override fun onDestroy() {
        super.onDestroy()
        authEmailPresenter.destroyObserver()
    }

    fun onBtnClickAuth(view: View){
        Log.d("***","AuthEmailActivity -> onBtnClickAuth")

        if (validateFormEmailAuth()) {
            val email = this.findViewById<EditText>(R.id.input_email).text.toString()
            val password = this.findViewById<EditText>(R.id.input_password).text.toString()
            authEmailPresenter.signIn(email, password)
        }
    }

    private fun validateFormEmailAuth(): Boolean {

        val email = this.findViewById<EditText>(R.id.input_email)
        val password = this.findViewById<EditText>(R.id.input_password)

        if (!ValidateUtils.isValidEmail(email.text)) {
            onWrongEmail(email)
            return false
        }

        if (!ValidateUtils.isValidPassword(password.toString())) {
            onWeakPassword(password)
            return false
        }

        return true
    }

    override fun onWrongEmail(email: EditText) {
        email.error = getString(R.string.ERROR_INVALID_EMAIL)
    }

    override fun onWeakPassword(password: EditText) {
        password.error = getString(R.string.ERROR_WEAK_PASSWORD)
    }

    override fun onSuccessAuth() {
        val intent = Intent(this, FriendsListActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun showFirebaseErrorMessage(errorType: ErrorsTypes){
        val res = resources
        val localized = res.getString(res.getIdentifier(errorType.name, "string", packageName))
        showError(localized)
    }


}


