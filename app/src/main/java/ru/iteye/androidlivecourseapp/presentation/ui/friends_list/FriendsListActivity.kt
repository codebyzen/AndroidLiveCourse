package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.os.Bundle
import android.util.Log
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.friends_list.FriendsListPresenter
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.friends_list.FriendsListView


class FriendsListActivity: BaseActivity(), FriendsListView {

    private val friendsListPresenter = FriendsListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list)

        friendsListPresenter.setView(this)

        friendsListPresenter.hello()

        Log.d("***", "FriendsListActivity CREATE")
    }

}