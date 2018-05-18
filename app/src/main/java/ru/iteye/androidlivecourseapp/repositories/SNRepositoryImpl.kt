package ru.iteye.androidlivecourseapp.repositories

import android.util.Log
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.data.SocialNetworks.SNAPI
import ru.iteye.androidlivecourseapp.domain.global.repositories.SNRepository
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskSNListener

class SNRepositoryImpl: SNRepository {

    companion object {
        val TAG = "*** SNRepositoryImpl"
    }

    private val snapi = SNAPI()

    override fun getFriends() : Observable<ArrayList<Friend>> {

        Log.d(TAG, " -> getFriends")

        return Observable.create<ArrayList<Friend>> { subscriber ->
            Log.d(TAG, " -> getFriends -> thread name: " + Thread.currentThread().name)

            snapi.getFriends(object : TaskSNListener {
                override fun onSuccess(result: ArrayList<Friend>) {
                    subscriber.onNext(result)
                    subscriber.onComplete()
                }
                override fun onError(exception: Exception?) {
                    subscriber.onError(exception)
                }
            })
        }
    }

}