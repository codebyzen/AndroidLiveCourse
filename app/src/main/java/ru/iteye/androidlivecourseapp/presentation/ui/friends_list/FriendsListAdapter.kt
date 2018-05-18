package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.friends_list_item.view.*


class FriendsListAdapter(private var friendsListData: ArrayList<Friend>): RecyclerView.Adapter<FriendsListHolder>() {

    private val TAG = "*** FriendsListAdapter"




    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.friends_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return FriendsListHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: FriendsListHolder, position: Int) {

        holder.bind(friendsListData[position])



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = friendsListData.size
}