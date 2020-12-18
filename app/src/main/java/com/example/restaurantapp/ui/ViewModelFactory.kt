package com.example.restaurantapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.data.RestaurantRepository

/**
 * https://developer.android.com/codelabs/kotlin-android-training-view-model#7
 * Factory for ViewModels
 * The factory method pattern is a creational design pattern that uses factory methods to create objects.
 * A factory method is a method that returns an instance of the same class.
 * Under the score package, create another Kotlin class called ViewModelFactory. This class will be responsible for instantiating the ViewModelFactory object.
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