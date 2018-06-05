package ru.iteye.androidlivecourseapp.presentation.ui.main_screen.fragments.friends

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.friends_list.*
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.presentation.mvp.main_screen.fragments.friends_list.FriendsListPresenter
import ru.iteye.androidlivecourseapp.presentation.mvp.main_screen.fragments.friends_list.FriendsListView
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseFragment


class FriendListFragment: BaseFragment(), FriendsListView {

    val TAG = "*** FriendListFragment"

    private var friendsListDataset: ArrayList<Friend> = arrayListOf()
    private lateinit var friendsListAdapter: FriendsListAdapter

    private val presenter = FriendsListPresenter()

    fun newInstance(): FriendListFragment {
        return FriendListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        prepareFriendsList()
        presenter.loadFriends()
        return inflater.inflate(R.layout.friends_list, container, false)
    }

    fun prepareFriendsList(){
        val myDataset: ArrayList<Friend> = friendsListDataset
        friendsListAdapter = FriendsListAdapter(myDataset)

        friends_list_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = friendsListAdapter
        }

        val friendsListLoadeingText = friends_list_loading_text
        friendsListLoadeingText.visibility = View.GONE
    }

    override fun appendFriendsList(myDataset: ArrayList<Friend>) {

        val itemsCount = friendsListAdapter.itemCount

        Log.d(TAG, " -> appendFriendsList -> friendsListRecycler items count is: $itemsCount")

        friendsListDataset.addAll(myDataset)

        Log.d(TAG, " -> appendFriendsList -> count of friendsListDataset is: " + myDataset.size.toString())
        friendsListAdapter.notifyItemRangeInserted(itemsCount, myDataset.size)

        Log.d(TAG, " -> appendFriendsList -> new count of friendsListDataset is: " + friendsListAdapter.itemCount.toString())
    }



    override fun showMessage(msg: String) {
        showMessage(msg)
    }

}