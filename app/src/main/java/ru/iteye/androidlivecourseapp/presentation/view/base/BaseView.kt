package ru.iteye.androidlivecourseapp.presentation.view.base

interface BaseView {
    fun showError(message : String, lambda: () -> Unit?)
}