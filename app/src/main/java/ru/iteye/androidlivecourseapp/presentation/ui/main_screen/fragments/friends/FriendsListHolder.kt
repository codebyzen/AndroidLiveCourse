package ru.iteye.androidlivecourseapp.presentation.ui.main_screen.fragments.friends

import android.support.v7.widget.RecyclerView
import android.view.View
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

        //Log.d(TAG, " : " + name + ' ' + friend.age + ' ' + friend.location)

        // Friend name
        listItem.friends_list_item_name.text = name

        // Friend Age
        if (friend.age.isNullOrEmpty()) {
            //TODO: тут надо у holder.listItem.findViewById<TextView>(R.id.friends_list_item_location) убрать левый margin
            listItem.friends_list_item_age.visibility = View.GONE
            //Log.d(TAG, " -> AGE IS GONE FOR : " + name + ' ' + friend.age + ' ' + friend.location)
        } else {
            listItem.friends_list_item_age.visibility = View.VISIBLE
            listItem.friends_list_item_age.text = friend.age.toString()
        }


        // Location
        if (friend.location.isNullOrEmpty()) {
            //Log.d(TAG, "Location is NULL for " + friend.firstName + ' ' + friend.lastName)
            listItem.friends_list_item_location.visibility = View.GONE
            //Log.d(TAG, " -> LOCATION IS GONE FOR : " + name + ' ' + friend.age + ' ' + friend.location)
        } else {
            listItem.friends_list_item_location.visibility = View.VISIBLE
            listItem.friends_list_item_location.text = friend.location
        }


        // Social network type
        if (friend.snType=="vk") {
            listItem.friends_list_item_sn_ic.setImageResource(R.drawable.ic_sign_vk)
        } else if(friend.snType=="fb") {
            listItem.friends_list_item_sn_ic.setImageResource(R.drawable.ic_sign_fb)
        }

        Picasso.with(listItem.friends_list_item_photo.context).load(photoUrl).into(listItem.friends_list_item_photo)

        listItem.friends_list_item_btn_like.setOnClickListener {
            friend.uid
        }


    }


}