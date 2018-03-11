package ru.iteye.androidlivecourseapp.presentation.mvp.global

open class BasePresenter<View> {

    private var aView: View? = null

    fun getView() : View? {
        return aView
    }

    fun setView(view : View?){
        this.aView = view
    }
}