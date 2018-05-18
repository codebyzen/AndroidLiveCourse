package ru.iteye.androidlivecourseapp.repositories.listeners

import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend

interface TaskSNListener {
    fun onSuccess(result : ArrayList<Friend>)
    fun onError(exception: Exception?)
}