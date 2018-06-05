package ru.iteye.androidlivecourseapp.presentation.mvp.main_screen.fragments.friends_list

import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface FriendsListView: BaseView {
    fun showMessage(msg: String)
    fun appendFriendsList(myDataset: ArrayList<Friend>)
}