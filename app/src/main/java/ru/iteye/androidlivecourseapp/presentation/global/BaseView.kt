package ru.iteye.androidlivecourseapp.presentation.global

interface BaseView {
    fun showError(message : String, lambda: () -> Unit?)
}