package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.utils.Friend
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintLayout






class FriendsListAdapter(private var friendsListData: ArrayList<Friend>): RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>() {

    private val TAG = "*** FriendsListAdapter"


    class FriendsViewHolder(val listItem: View) : RecyclerView.ViewHolder(listItem) {
        private lateinit var textName: TextView
        init {
            textName = listItem.findViewById<TextView>(R.id.friends_list_item_name)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListAdapter.FriendsViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.friends_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return FriendsViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val name = friendsListData[position].firstName + ' ' + friendsListData[position].lastName
        val photoUrl = friendsListData[position].photoSmall

        holder.listItem.findViewById<TextView>(R.id.friends_list_item_name).text = name

        if (friendsListData[position].age.toString()=="") {
            holder.listItem.findViewById<TextView>(R.id.friends_list_item_age).visibility = View.GONE

            //TODO: тут надо у holder.listItem.findViewById<TextView>(R.id.friends_list_item_location) убрать левый margin

        } else {
            holder.listItem.findViewById<TextView>(R.id.friends_list_item_age).text = friendsListData[position].age.toString()
        }

        if (friendsListData[position].location=="") {
            holder.listItem.findViewById<TextView>(R.id.friends_list_item_location).visibility = View.GONE
        } else {
            holder.listItem.findViewById<TextView>(R.id.friends_list_item_location).text = friendsListData[position].location
        }


        if (friendsListData[position].snType=="vk") {
            holder.listItem.findViewById<ImageView>(R.id.friends_list_item_sn_ic).setImageResource(R.drawable.ic_sign_vk)
        } else if(friendsListData[position].snType=="fb") {
            holder.listItem.findViewById<ImageView>(R.id.friends_list_item_sn_ic).setImageResource(R.drawable.ic_sign_fb)
        }

        val imageView = holder.listItem.findViewById<ImageView>(R.id.friends_list_item_photo)
        Picasso.with(holder.listItem.context).load(photoUrl).into(imageView)


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = friendsListData.size
}