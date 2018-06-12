package ru.iteye.androidlivecourseapp.presentation.ui.global

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

open class BaseFragment: Fragment(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseFragment")
    }

    private fun showToast(msg: String) {
        Toast.makeText(parentFragment?.context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showSnack(msg: String) {

        val actP = activity?.findViewById<View>(R.id.main_screen_view) ?: return

        val snack: Snackbar = Snackbar.make(actP.rootView, msg, Snackbar.LENGTH_LONG)
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
        Log.d("***", "BaseFragment -> showError")
        val context = parentFragment?.context ?: return
        AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.btn_text_ok, { dialog, ok ->
                    lambda()
                    dialog.dismiss()
                })
                .show()
    }
}