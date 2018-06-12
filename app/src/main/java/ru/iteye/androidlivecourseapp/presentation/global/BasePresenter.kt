package ru.iteye.androidlivecourseapp.presentation.global

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<View> {

    val disposables = CompositeDisposable()

    fun destroyObserver(){
        disposables.clear()
    }

    private var aView: View? = null

    fun getView() : View? {
        return aView
    }

    fun setView(view : View?){
        this.aView = view
    }
}