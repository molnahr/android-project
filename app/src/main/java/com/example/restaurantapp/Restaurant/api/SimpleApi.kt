package com.example.restaurantapp.Restaurant.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Github API communication setup via Retrofit.
 */
interface SimpleApi {
    @GET("restaurants")
    suspend fun searchRestaurants(
            @Query("city") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int
    ): RestaurantSearchResponse

    companion object {
        private const val BASE_URL = "https://ratpark-api.imok.space/"

        fun create(): SimpleApi {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SimpleApi::class.java)
        }
    }
}