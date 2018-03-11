package ru.iteye.androidlivecourseapp.presentation.ui.global

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.widget.TextView
import android.view.Gravity
import android.widget.FrameLayout
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView


open class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun showSnack(msg: String) {
        val snack: Snackbar = Snackbar.make(window.decorView.rootView, msg, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }

    fun showMessage(msg: String, isToast: Boolean) {
        if (isToast) {
            showToast(msg)
        } else {
            showSnack(msg)
        }
    }

    fun showCustomAlert(customMessage: String) {
        AlertDialog.Builder(this)
                .setMessage(customMessage)
                .setPositiveButton(R.string.btn_text_ok, { dialog, ok -> dialog.dismiss() })
                .show()
    }

    override fun showError(message: String) {
        Log.d("***", "BaseActivity -> showError")
        showCustomAlert(message)
    }

}

