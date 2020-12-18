package com.example.restaurantapp.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.restaurantapp.model.Restaurant


/**
 * Adapter for the list of repositories.
 * Automaticaly generated for us, we just added some code.
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