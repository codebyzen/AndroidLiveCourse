package ru.iteye.androidcourseproject01.presentation.mvp.global

import android.content.Context

open class BasePresenter<View> {

    var aView: View? = null

    fun getView() : View? {
        return aView
    }

    fun setView(view : View?){
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