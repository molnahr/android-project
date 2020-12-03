package com.example.restaurantapp.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.data.RestaurantRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: RestaurantRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRestaurantsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchRestaurantsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}