package com.example.restaurantapp.util

import com.example.restaurantapp.api.SimpleApi
import com.example.restaurantapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
                // BASE_URL hibas volt..
                .baseUrl("https://opentable.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}