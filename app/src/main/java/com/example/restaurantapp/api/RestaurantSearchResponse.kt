package com.example.restaurantapp.api

import com.example.restaurantapp.model.Restaurant
import com.google.gson.annotations.SerializedName

/**
 * Data class to hold repo responses from searchRepo API calls.
 */
data class RestaurantSearchResponse(
        @SerializedName("total_entries") val total: Int = 0,
        @SerializedName("per_page") val per_page: Int = 0,
        @SerializedName("current_page") val current_page: Int = 0,
        @SerializedName("restaurants") val items: List<Restaurant> = emptyList(),
        val nextPage: Int? = null
)
