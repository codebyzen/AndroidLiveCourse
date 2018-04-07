package ru.iteye.androidlivecourseapp.repositories.listeners

import com.google.firebase.auth.AuthResult

interface TaskAuthFirebaseListener {
    fun onSuccess(result : AuthResult)
    fun onComplete()
    fun onError(exception: Exception?)
}