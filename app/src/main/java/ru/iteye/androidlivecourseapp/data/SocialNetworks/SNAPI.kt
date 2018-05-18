package ru.iteye.androidlivecourseapp.data.SocialNetworks

import android.util.Log
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskSNListener

class SNAPI {

    companion object {
        val TAG = "*** SNAPI"
    }

    val apiVK = APIVK()

    fun getActiveNetworsk(): Array<String>{
        Log.d(TAG, " -> getActiveNetworsk")
        return arrayOf("vk")
    }

    fun getFriends(listener: TaskSNListener){
        Log.d(TAG, " -> getFriends")

        val snetworks = getActiveNetworsk()
        for (type in snetworks) {
            if (type=="vk") {
                getVKFriends(listener)
            } else {
                getFBFriends(listener)
            }
        }
    }

    fun getVKFriends(listener: TaskSNListener){
        Log.d(TAG, " -> getVKFriends")
        apiVK.getFriends(listener)

    }

    fun getFBFriends(listener: TaskSNListener){
        Log.d(TAG, " -> getFBFriends")

    }

}