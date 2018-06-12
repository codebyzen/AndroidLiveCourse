package ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.friends_list

import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.presentation.global.BaseView

interface FriendsListView: BaseView {
    fun showMessage(msg: String)
    fun appendFriendsList(myDataset: ArrayList<Friend>)
}