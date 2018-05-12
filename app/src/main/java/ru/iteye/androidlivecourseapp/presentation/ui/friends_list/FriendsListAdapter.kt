package ru.iteye.androidlivecourseapp.presentation.ui.friends_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.iteye.androidlivecourseapp.R

class FriendsListAdapter(private var myDataset: Array<String>): RecyclerView.Adapter<FriendsListAdapter.ViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val listItem: View) : RecyclerView.ViewHolder(listItem)


    var items: Array<String> = arrayOf()


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FriendsListAdapter.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.friends_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.listItem.findViewById<TextView>(R.id.friends_list_item_name).text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}