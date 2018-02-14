package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.util.Log

//todo: Я не понимаю зачем нам это
/**
* Дальше мы будем наследовать все Activity от этого класса.
 * Например, нам надо показать ошибку от сервера. Чаще всего, это диалог, где показывается текст и кнопка "ОК"
 * Функцию показа этого диалога можно сделать только тут, и просто ее вызывать. Это избавит от излишнего кода
 *
 *
 * Или же надо делать действия, необходимые на каждом экране. Например, проверка токена пользователя при переходе на активити.
 * Логику можно также описать только в базовой активити
 */
open class BaseActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }
}


