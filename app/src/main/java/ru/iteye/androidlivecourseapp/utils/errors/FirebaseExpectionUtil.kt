package ru.iteye.androidlivecourseapp.utils.errors

class FirebaseExpectionUtil(message: String, val type: ErrorsTypes) : Exception(message)
