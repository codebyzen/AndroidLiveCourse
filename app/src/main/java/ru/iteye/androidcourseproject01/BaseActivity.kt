package ru.iteye.androidcourseproject01

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView


/**
* Дальше мы будем наследовать все Activity от этого класса.
 * Например, нам надо показать ошибку от сервера. Чаще всего, это диалог, где показывается текст и кнопка "ОК"
 * Функцию показа этого диалога можно сделать только тут, и просто ее вызывать. Это избавит от излишнего кода
 *
 *
 * Или же надо делать действия, необходимые на каждом экране. Например, проверка токена пользователя при переходе на активити.
 * Логику можно также описать только в базовой активити
 */
open class BaseActivity : AppCompatActivity() {

    private var cView: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }

    fun setContext(c: Context) {
        cView = c
    }

    /**
     * Показываем всплывашку
     */
    fun showToast(msg: String) {
        //Log.d("***", this.packageName.toString())
        Toast.makeText(this.cView, msg, Toast.LENGTH_LONG).show()
    }

    /**
     * Simple alert dialog for some debug purposes
     */
    fun showCustomAlert(customMessage: String, isExit: Boolean){
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog,null)
        val alertMessage = dialogView.findViewById<TextView>(R.id.textAlertMessage)
        dialog.setView(dialogView)
        //dialog.setCancelable(true)
        dialog.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->
            if (isExit) System.exit(0)
        })
        alertMessage.text = customMessage
        val customDialog = dialog.create()
        customDialog.show()

    }

}


