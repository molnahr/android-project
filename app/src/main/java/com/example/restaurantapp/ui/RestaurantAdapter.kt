package com.example.restaurantapp.ui

import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.restaurantapp.model.Restaurant
import fragments.ProfileFragmentDirections
import kotlinx.android.synthetic.main.row_item.view.*


/**
 * Adapter for the list of repositories.
 */
class RestaurantAdapter : ListAdapter<Restaurant, androidx.recyclerview.widget.RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return RestaurantViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as RestaurantViewHolder).bind(repoItem)
        }
        //https://www.youtube.com/watch?v=5rfBU75sguk 6:40
//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ProfileFragmentDirections.actionProfileFragmentToChangeProfil()
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                    oldItem.id == newItem.id
        }
    }
}