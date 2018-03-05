package ru.iteye.androidcourseproject01.presentation.ui.friends_list

import android.os.Bundle
import android.util.Log
import ru.iteye.androidcourseproject01.R
import ru.iteye.androidcourseproject01.presentation.mvp.friends_list.FriendsListPresenter
import ru.iteye.androidcourseproject01.presentation.ui.global.BaseActivity
import ru.iteye.androidcourseproject01.presentation.mvp.friends_list.FriendsListView


class FriendsListActivity: BaseActivity(), FriendsListView {

    private val friendsListPresenter = FriendsListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list)

        friendsListPresenter.setView(this)

        Log.d("***", "FriendsListActivity CREATE")
    }

    override fun showError(message: String) {
        showCustomAlert(message)
    }

}