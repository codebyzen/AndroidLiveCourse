package ru.iteye.androidlivecourseapp.presentation.mvp.auth_choose

import android.content.Intent
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface AuthChooseView: BaseView {
    fun startAuthEmailActivity()
    fun onSuccessAuth()
    fun superOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun onAuthVkFail()
}