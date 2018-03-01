package ru.iteye.androidcourseproject01.presentation.global

import android.content.Context

open class BasePresenter<T> {

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