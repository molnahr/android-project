package com.example.restaurantapp.ui


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.restaurantapp.R
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.example.restaurantapp.model.Restaurant
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.text_view_name)
    private val address: TextView = view.findViewById(R.id.text_view_addres)
    private val image: ImageView = view.findViewById(R.id.image_view)
    private val price: TextView = view.findViewById(R.id.text_view_price)

    private var restaurant: Restaurant? = null
    var navController: NavController? = null

    //ezzel lehet baj!
    init {
        itemView.setOnClickListener { v: View ->
            navController!!.navigate(R.id.action_listOfRestaurantsFragment_to_detailsFragment)
        }

    }

//kiegesziteni
fun bind(restaurant: Restaurant?) {
    if (restaurant == null) {
        val resources = itemView.resources
        name.text = resources.getString(R.string.loading)
        address.visibility = View.GONE
        price.text = resources.getString(R.string.unknown)
//        Glide.with(image)
//                .load(R.drawable.splashscreen)
//                .placeholder(R.drawable.splashscreen)
//                .into(image)
    } else {
        showRestaurantData(restaurant)
    }
}

private fun showRestaurantData(restaurant: Restaurant) {
    this.restaurant = restaurant
    name.text = restaurant.name
    address.text = restaurant.address
    price.text = restaurant.price.toString()
//    Glide.with(image)
//            .load(restaurant.image_url)
//            .centerInside()
//            .placeholder(R.drawable.splashscreen)
//            .into(image)

}

companion object {
    fun create(parent: ViewGroup): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
        return RestaurantViewHolder(view)
    }
}
}
