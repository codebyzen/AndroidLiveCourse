package ru.iteye.androidlivecourseapp.domain.global.repositories

import io.reactivex.Observable

interface UserRepository {
    fun createUser(userData: HashMap<String, Any>): Observable<Boolean>
    fun getUser(): Observable<HashMap<String, Any>>
    fun setLike(to: String): Observable<Boolean>
    fun getLikes(owner: String): Observable<HashMap<String, Any>>
    fun checkLikesCross(): Observable<ArrayList<String>>
    fun checkPremium(): Observable<Boolean>
    fun checkAdmin(): Observable<Boolean>
}