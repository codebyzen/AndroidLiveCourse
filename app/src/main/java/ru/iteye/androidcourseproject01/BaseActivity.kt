package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.app.Activity





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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }

    /**
     *  чтобы класс наследник вызывая например showToast
     *  мог быть в контексте текущей активности
     */
    //TODO: для всплывашки
    private var mCurrentActivity: Activity? = null
    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }
    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }


    /**
     * Показываем всплывашку
     */
    //TODO: никак не получается в контексте активной активити вывести всплывашку =((((
    fun showToast(msg: String) {
        Toast.makeText(getCurrentActivity(), msg, Toast.LENGTH_LONG).show()
    }

}


