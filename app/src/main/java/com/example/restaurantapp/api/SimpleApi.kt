package com.example.restaurantapp.api

import com.example.restaurantapp.model.Restaurant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

/**
 * Github API communication setup via Retrofit.
 */
interface SimpleApi {
    /**
     * Get repos ordered by stars.
     */
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
            @Query("country") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int
    ): RestaurantSearchResponse

    companion object {
        private const val BASE_URL = "https://opentable.herokuapp.com/api/"

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