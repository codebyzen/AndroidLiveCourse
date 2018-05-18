package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend

interface SNRepository {
    fun getFriends(): Observable<ArrayList<Friend>>
}