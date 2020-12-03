package com.example.restaurantapp.repository

import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.util.RetrofitInstance
import retrofit2.Response

class  Repository {

    suspend fun getRestaurant(): Restaurant{
        return RetrofitInstance.api.getRestaurant()
    }

    suspend fun getCustomRestaurants(id: Int, sort: String, order: String): Response<List<Restaurant>>{
        return RetrofitInstance.api.getCustomRestaurants(id, sort, order)
    }

    suspend fun getCustomRestaurants2(id: Int, options: Map<String,String>): Response<List<Restaurant>>{
        return RetrofitInstance.api.getCustomRestaurants2(id,options)
    }
}