package ru.iteye.androidlivecourseapp.presentation.ui.global

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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

    override fun showError(message: String, lambda: () -> Unit?) {
        Log.d("***", "BaseActivity -> showError")
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.btn_text_ok, { dialog, ok ->
                    lambda()
                    dialog.dismiss()
                })
                .show()
    }




}


