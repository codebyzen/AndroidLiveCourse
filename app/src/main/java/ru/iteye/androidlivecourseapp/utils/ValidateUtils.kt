package ru.iteye.androidlivecourseapp.utils

import android.text.TextUtils

object ValidateUtils {
    fun isValidEmail(email: CharSequence): Boolean = !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isValidPassword(password: CharSequence): Boolean = !TextUtils.isEmpty(password) && password.length>=6
}