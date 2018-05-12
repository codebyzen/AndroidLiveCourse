package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import android.content.Context
import android.util.Log
import com.vk.sdk.api.*
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList

class FriendsListPresenter: BasePresenter<FriendsListView>() {

    private val TAG = "***FriendsListPresenter"

    fun hello(){
        //Get user info
        VKApi.users().get().executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                val user = (response!!.parsedModel as VKList<VKApiUser>)[0]
                getView()?.showMessage("Привет ${user.first_name} ${user.last_name}")
            }
        })
    }


    fun loadFriends(ctx: Context) {
        Log.d(TAG, " -> loadFriends")
        VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"nickname")).executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {

                val users = response!!.parsedModel as VKList<VKApiUser>
                var myDataset: Array<String> = arrayOf()
                for (user in users) {
                    val name = user.first_name + ' ' + user.last_name
                    myDataset = myDataset.plus(name)
                }
                getView()?.appendFriendsList(myDataset)
            }
        })




    }


}