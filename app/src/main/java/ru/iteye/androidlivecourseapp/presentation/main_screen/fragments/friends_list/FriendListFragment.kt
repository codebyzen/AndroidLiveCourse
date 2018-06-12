package ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.friends_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.friends_list.*
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend


class FriendListFragment: Fragment(), FriendsListView {

    val TAG = "*** FriendListFragment"

    private var friendsListDataset: ArrayList<Friend> = arrayListOf()
    private lateinit var friendsListAdapter: FriendsListAdapter

    private val presenter = FriendsListPresenter()

    fun newInstance(): FriendListFragment {
        return FriendListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.setView(this)
        prepareFriendsList()
        presenter.loadFriends()
        return inflater.inflate(R.layout.friends_list, container, false)
    }

    fun prepareFriendsList(){
        val myDataset: ArrayList<Friend> = friendsListDataset
        friendsListAdapter = FriendsListAdapter(myDataset)

        val fragment_recycler = activity?.findViewById<RecyclerView>(R.id.friends_list_recycler)

        if (fragment_recycler == null) {} else {
            fragment_recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
                adapter = friendsListAdapter
            }
            val friendsListLoadeingText = friends_list_loading_text
            friendsListLoadeingText.visibility = View.GONE
        }

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

    override fun showError(message: String, lambda: () -> Unit?) {
        showError(message, lambda)
    }

}