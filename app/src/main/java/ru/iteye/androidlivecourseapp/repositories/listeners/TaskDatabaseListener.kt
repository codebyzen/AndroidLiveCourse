package ru.iteye.androidlivecourseapp.repositories.listeners

interface TaskDatabaseListener {
    fun onWriteSuccess(result : String)
    fun onReadSuccess(result: HashMap<String, Any>)
    fun onError(exception: Exception?)
}