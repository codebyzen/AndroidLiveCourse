package ru.iteye.androidlivecourseapp.domain.SocialNetworks

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.domain.global.repositories.SNRepository

class SNInteractor(private val repository: SNRepository) {

    companion object {
        val TAG =  "*** SNInteractor"
    }

    fun getFriends(): Observable<ArrayList<Friend>> {
        Log.d(TAG, " -> getFriends")
        return repository.getFriends()
    }

}