package com.example.restaurantapp.model

/**
 * Immutable model class for a Github repo that holds all the information about a repository.
 * Objects of this type are received from the Github API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the repo [id] is the primary key.
 */
data class Restaurant(
        val id: Long,
        val name: String,
        val address: String,
        val city: String,
        val state: String,
        val area: String,
        val postal_code: Number,
        val country: String,
        val phone: String,
        val lat: Number,
        val lng: Number,
        val price: Number,
        val reserve_url: String,
        val mobile_reserve_url: String,
        val image_url: String
)

object Supplier {
    val restaurants = ArrayList<Restaurant>()
}