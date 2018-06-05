package ru.iteye.androidlivecourseapp.presentation.ui.auth_email

import android.util.Log
import android.widget.EditText
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailPresenter
import ru.iteye.androidlivecourseapp.presentation.ui.main_screen.MainScreenActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.auth_email.AuthEmailView
import ru.iteye.androidlivecourseapp.presentation.ui.reg_email.RegEmailActivity
import ru.iteye.androidlivecourseapp.utils.ValidateUtils
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes


class AuthEmailActivity : BaseActivity(), AuthEmailView {

    private val authEmailPresenter = AuthEmailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_email)

        authEmailPresenter.setView(this)

        val mProgressBar = this.findViewById<ProgressBar>(R.id.progressBar)
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE

        if (android.os.Build.VERSION.SDK_INT > 21 ) {
            mProgressBar.elevation = 2f
            mProgressBar.translationZ =  2f
            mProgressBarFrame.elevation = 2f
            mProgressBarFrame.translationZ = 2f
        }

        Log.d("***", "AuthEmailActivity created!")
    }

    override fun onDestroy() {
        super.onDestroy()
        authEmailPresenter.destroyObserver()
    }

    fun onBtnClickAuth(view: View){
        Log.d("***","AuthEmailActivity -> onBtnClickAuth")

        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.VISIBLE

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
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE
        email.error = getString(R.string.ERROR_INVALID_EMAIL)
    }

    override fun onWeakPassword(password: EditText) {
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE
        password.error = getString(R.string.ERROR_WEAK_PASSWORD)
    }

    override fun onSuccessAuth() {
        val mProgressBarFrame = this.findViewById<FrameLayout>(R.id.progressBarFrame)
        mProgressBarFrame.visibility = View.GONE
        val intent = Intent(this, MainScreenActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun showFirebaseErrorMessage(errorType: ErrorsTypes){
        val res = resources
        val localized = res.getString(res.getIdentifier(errorType.name, "string", packageName))
        showError(localized, {})
    }


    fun onBtnClickRegEmail(view: View) {
        Log.d("***", "AuthChooseActivity -> onBtnClickRegEmail")
        authEmailPresenter.startRegEmailActivity()
    }

    override fun startRegEmailActivity(){
        val intent = Intent(this, RegEmailActivity::class.java)
        startActivity(intent)
    }


}


