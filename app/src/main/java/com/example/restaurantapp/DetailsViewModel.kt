package com.example.restaurantapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(private val repository: Repository): ViewModel(){
    val myResponse: MutableLiveData<Restaurant> = MutableLiveData()
    fun gerRestaurant(){
        viewModelScope.launch {
            val response = repository.getRestaurant()
            myResponse.value = response
        }
    }
}