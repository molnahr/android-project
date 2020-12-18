package com.example.restaurantapp.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.restaurantapp.R
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.example.restaurantapp.model.Restaurant
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.row_item.view.*

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.text_view_name)
    private val address: TextView = view.findViewById(R.id.text_view_addres)
    private val price: TextView = view.findViewById(R.id.text_view_price)

    private var restaurant: Restaurant? = null

    /**
     * if we kick an itemView element we will be navigate to the DetailsFragment what include
     * the details of the selected Restaurant-item and send the arguments.
     */
    init {
        itemView.setOnClickListener {
            val action =
                ListOfRestaurantsFragmentDirections.actionListOfRestaurantsFragmentToDetailsFragment(
                    adapterPosition
                )
            Navigation.findNavController(itemView).navigate(action)
        }
    }

    // We need this for onBindViewHolder..RestaurantAdapter.
    fun bind(restaurant: Restaurant?) {
        if (restaurant == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            address.visibility = View.GONE
            price.text = resources.getString(R.string.unknown)
        } else {
            showRestaurantData(restaurant)
        }
    }

    // We can see this fields in our rowItem.
    private fun showRestaurantData(restaurant: Restaurant) {
        this.restaurant = restaurant
        name.text = restaurant.name
        address.text = restaurant.address
        price.text = restaurant.price.toString()
    }

    // Create restaurant RestaurantViewHolder.
    companion object {
        fun create(parent: ViewGroup): RestaurantViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
            return RestaurantViewHolder(view)
        }
    }
}
