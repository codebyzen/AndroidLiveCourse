package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend

interface FriendsListView: BaseView {
    fun prepareFriendsList()
    fun appendFriendsList(myDataset: ArrayList<Friend>)
    fun showMessage(msg: String)
}