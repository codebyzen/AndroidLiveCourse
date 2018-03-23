package ru.iteye.androidlivecourseapp.data.repositories.listeners

import com.google.firebase.auth.AuthResult

interface TaskAuthFirebaseListener {
    fun onSuccess(result : AuthResult)
    fun onError(exception: Exception?)
}