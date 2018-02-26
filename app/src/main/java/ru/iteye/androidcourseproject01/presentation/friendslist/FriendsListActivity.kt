package ru.iteye.androidcourseproject01.presentation.friendslist

import android.os.Bundle
import android.util.Log
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.global.BaseActivity
import ru.iteye.androidcourseproject01.presentation.view.friendslist.FriendsListView


class FriendsListActivity: BaseActivity(), FriendsListView {

    private val friendsListPresenter = FriendsListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list)

        friendsListPresenter.setView(this)

        Log.d("***", "FriendsListActivity CREATE")
    }

}