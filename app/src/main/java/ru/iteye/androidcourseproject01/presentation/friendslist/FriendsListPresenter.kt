package ru.iteye.androidcourseproject01.presentation.friendslist

import ru.iteye.androidcourseproject01.presentation.view.friendslist.FriendsListView

/**
 * Created by ugputu on 26/02/2018.
 */
class FriendsListPresenter {

    private var view : FriendsListView? = null

    fun getView() : FriendsListView? {
        return view
    }

    fun setView(view : FriendsListView?){
        this.view = view
    }

}