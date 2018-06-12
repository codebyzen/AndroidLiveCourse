package ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.friends_list

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.domain.SocialNetworks.SNInteractor
import ru.iteye.androidlivecourseapp.presentation.global.BasePresenter
import ru.iteye.androidlivecourseapp.repositories.SNRepositoryImpl

class FriendsListPresenter: BasePresenter<FriendsListView>() {

    private val TAG = "***FriendsListPresenter"

    private var interactor = SNInteractor(SNRepositoryImpl())

    fun loadFriends() {
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