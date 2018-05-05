package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import com.vk.sdk.api.VKResponse
import com.vk.sdk.api.VKRequest
import com.vk.sdk.api.VKApi




class FriendsListPresenter: BasePresenter<FriendsListView>() {

    fun hello(){


        //Get user info
        VKApi.users().get().executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                val user = (response!!.parsedModel as VKList<VKApiUser>)[0]
                getView()?.showError("Привет ${user.first_name} ${user.last_name}", {})
            }
        })

    }



}