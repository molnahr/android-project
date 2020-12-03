package com.example.restaurantapp.data

import com.example.restaurantapp.model.Restaurant
import java.lang.Exception

/**
 * RepoSearchResult from a search, which contains List<Repo> holding query data,
 * and a String of network error state.
 */
sealed class RestaurantSearchResult {
    data class Success(val data: List<Restaurant>) : RestaurantSearchResult()
    data class Error(val error: Exception) : RestaurantSearchResult()
}
