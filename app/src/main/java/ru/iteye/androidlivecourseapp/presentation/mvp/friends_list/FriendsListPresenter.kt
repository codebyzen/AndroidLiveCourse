package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import android.content.Context
import android.util.Log
import com.vk.sdk.api.*
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.domain.SocialNetworks.SNInteractor
import ru.iteye.androidlivecourseapp.repositories.SNRepositoryImpl
import kotlin.collections.ArrayList


class FriendsListPresenter: BasePresenter<FriendsListView>() {

    private val TAG = "***FriendsListPresenter"

    private var interactor = SNInteractor(SNRepositoryImpl())

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

        disposables.add(
                interactor.getFriends()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ friendsList ->
                            Log.d(TAG, " -> loadFriends -> thread name: " + Thread.currentThread().name)
                            onFriendsGetted(friendsList)
                        }, { error ->
                            error.printStackTrace()
                            onFriendsFailed(error)
                        })
        )
    }

    fun onFriendsGetted(myDataset: ArrayList<Friend>){
        Log.d(TAG, " -> onFriendsGetted")
        for (i in myDataset) {
            //Log.d(TAG, " : " + i.firstName + ' ' + i.lastName + ' ' + i.age + ' ' + i.location)
        }
        getView()?.appendFriendsList(myDataset)
    }

    fun onFriendsFailed(error: Throwable) {
        Log.d(TAG, " -> onFriendsFailed")
        getView()?.showError(error.message!!, {})
    }

}