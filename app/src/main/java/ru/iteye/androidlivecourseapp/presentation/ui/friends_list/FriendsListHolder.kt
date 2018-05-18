package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.friends_list_item.view.*
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend

class FriendsListHolder(val listItem: View) : RecyclerView.ViewHolder(listItem) {

    companion object {
        val TAG = "*** FriendsListHolder"
    }

    fun bind(friend: Friend) {

        val name = friend.firstName + ' ' + friend.lastName
        val photoUrl = friend.photoSmall

        // Friend name
        listItem.friends_list_item_name.text = name

        // Friend Age
        if (friend.age.isNullOrEmpty()) {
            //TODO: тут надо у holder.listItem.findViewById<TextView>(R.id.friends_list_item_location) убрать левый margin
            listItem.friends_list_item_age.visibility = View.GONE
        } else {
            listItem.friends_list_item_age.visibility = View.VISIBLE
            listItem.friends_list_item_age.text = friend.age.toString()
        }


        // Location
        if (friend.location=="") {
            //Log.d(TAG, "Location is NULL for " + friend.firstName + ' ' + friend.lastName)
            listItem.friends_list_item_location.visibility = View.GONE
        } else {
            listItem.friends_list_item_location.text = friend.location
        }


        // Social network type
        if (friend.snType=="vk") {
            listItem.friends_list_item_sn_ic.setImageResource(R.drawable.ic_sign_vk)
        } else if(friend.snType=="fb") {
            listItem.friends_list_item_sn_ic.setImageResource(R.drawable.ic_sign_fb)
        }

        val imageView = listItem.findViewById<ImageView>(R.id.friends_list_item_photo)
        Picasso.with(listItem.context).load(photoUrl).into(imageView)
    }


}