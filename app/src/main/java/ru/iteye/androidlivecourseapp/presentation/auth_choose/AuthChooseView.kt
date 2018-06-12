package ru.iteye.androidlivecourseapp.presentation.auth_choose

import android.content.Intent
import ru.iteye.androidlivecourseapp.presentation.global.BaseView

interface AuthChooseView: BaseView {
    fun startAuthEmailActivity()
    fun onSuccessAuth()
    fun superOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun toggleProgressBar(letsShow: Boolean?)
    fun onAuthFail(error: String?)

}