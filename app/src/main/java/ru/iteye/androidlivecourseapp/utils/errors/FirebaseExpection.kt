package ru.iteye.androidlivecourseapp.utils.errors

class FirebaseExpection(message: String, val type: ErrorsTypes) : Exception(message)
