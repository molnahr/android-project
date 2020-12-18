package com.example.restaurantapp.data


import android.util.Log
import com.example.restaurantapp.Restaurant.api.SimpleApi
import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.model.Supplier
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException

private const val REPO_STARTING_PAGE_INDEX = 1

/**
 * Repository class that works with local and remote data sources.
 */

class RestaurantRepository(private val service: SimpleApi) {

    // keep the list of all results received
    val inMemoryCache = mutableListOf<Restaurant>()

    // keep channel of results. The channel allows us to broadcast updates so
    // the subscriber will have the latest data
    private val searchResults = ConflatedBroadcastChannel<RestaurantSearchResult>()

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = REPO_STARTING_PAGE_INDEX

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Search repositories whose city match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    @FlowPreview
    suspend fun getSearchResultStream(query: String): Flow<RestaurantSearchResult> {
        Log.d("Repository", "New query: $query")
        lastRequestedPage = 1
        inMemoryCache.clear()
        requestAndSaveData(query)
        return searchResults.asFlow()
    }

    // Request more page.
    suspend fun requestMore(query: String) {
        if (isRequestInProgress) return
        val successful = requestAndSaveData(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    // query ex: Dallas
    private suspend fun requestAndSaveData(query: String): Boolean {
        isRequestInProgress = true
        var successful = false
        try {
            // The respons to our query. lastRequestedPage=REPO_STARTING_PAGE_INDEX,
                // NETWORK_PAGE_SIZE static 25
            val response = service.searchRestaurants(query, lastRequestedPage, NETWORK_PAGE_SIZE)
            val restaurants = response.items ?: emptyList()
            inMemoryCache.addAll(restaurants)
            Supplier.restaurants.addAll(restaurants)
            val restaurantCity = reposByCity(query)
            searchResults.offer(RestaurantSearchResult.Success(restaurantCity))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(RestaurantSearchResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(RestaurantSearchResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    // Serch by city.
    private fun reposByCity(query: String): List<Restaurant> {
        Log.i("query", query)
        // from the in memory cache select only the repos whose name matches
        // the query
        return inMemoryCache.filter {
            it.city.contains(query, true) ||
                    (it.city != null)
        }
    }

    // Number of items in recyclerView pages.
    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}
