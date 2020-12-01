package com.example.restaurantapp.api

import com.example.restaurantapp.model.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("restaurants/100")
    suspend fun getRestaurant(): Restaurant


    @GET("restaurants")
    suspend fun getCustomRestaurants(
            @Query("id") id: Int,
            @Query("_sort") sort: String,
            @Query("_order") order: String
    ): Response<List<Restaurant>>
}