package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.os.Bundle
import android.util.Log
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.mvp.friends_list.FriendsListPresenter
import ru.iteye.androidlivecourseapp.presentation.ui.global.BaseActivity
import ru.iteye.androidlivecourseapp.presentation.mvp.friends_list.FriendsListView
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.friends_list.*
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend


class FriendsListActivity: BaseActivity(), FriendsListView {

    private val TAG = "***FriendsListActivity"

    private val friendsListPresenter = FriendsListPresenter()

    private var friendsListDataset: ArrayList<Friend> = arrayListOf()
    private lateinit var friendsListAdapter: FriendsListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list)

        friendsListPresenter.setView(this)

        friendsListPresenter.hello()

        val bottomNavigationView = friends_list_menu_bottom

        //TODO: убрать текст в стринги
        bottomNavigationView.setOnNavigationItemSelectedListener(
                object : BottomNavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        when (item.itemId) {
                            R.id.action_friends -> {
                                showMessage("Друзья", true)
                            }
                            R.id.action_connections -> {
                                showMessage("Совпадения", true)
                            }
                            R.id.action_settings -> {
                                showMessage("Настройки", true)
                            }
                        }
                        return true
                    }
                })

        prepareFriendsList()
        friendsListPresenter.loadFriends(this)

        Log.d(TAG, "FriendsListActivity CREATE")
    }


    override fun prepareFriendsList(){
        val myDataset: ArrayList<Friend> = friendsListDataset
        friendsListAdapter = FriendsListAdapter(myDataset)

        FriendsList.apply {
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
        showMessage(msg, true)
    }

}