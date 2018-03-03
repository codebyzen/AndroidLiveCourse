package ru.iteye.androidcourseproject01.presentation.mvp.global

import android.content.Context
import android.view.View

open class BasePresenter<T: View> {

    var aView: T? = null

    fun getView() : T? {
        return aView
    }

    fun setView(view : T?){
        this.aView = view
    }

    private var aContext : Context? = null

    fun setContext(context: Context) {
        this.aContext = context
    }

    fun getContext(): Context? {
        return this.aContext
    }

}