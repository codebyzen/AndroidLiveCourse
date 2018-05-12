package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface FriendsListView: BaseView {
    fun prepareFriendsList()
    fun appendFriendsList(myDataset: Array<String>)
    fun showMessage(msg: String)
}