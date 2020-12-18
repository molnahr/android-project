package com.example.restaurantapp.ui

import androidx.lifecycle.*
import com.example.restaurantapp.data.RestaurantRepository
import com.example.restaurantapp.data.RestaurantSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * ViewModel for the [SearchRestaurantsViewModel] screen.
 * The ViewModel works with the [RestaurantRepository] to get the data.
 */
@ExperimentalCoroutinesApi
class SearchRestaurantsViewModel(private val repository: RestaurantRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    val RestaurantResult: LiveData<RestaurantSearchResult> =
        queryLiveData.switchMap { queryString ->
            liveData {
                val restaurant =
                    repository.getSearchResultStream(queryString).asLiveData(Dispatchers.Main)
                emitSource(restaurant)
            }
        }

    /**
     * Search a repository based on a query string.
     */
    fun searchRestaurant(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    /**
     * List our 25 item. Then we can scrole them.
     */
    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = queryLiveData.value
            if (immutableQuery != null) {
                viewModelScope.launch {
                    repository.requestMore(immutableQuery)
                }
            }
        }
    }
}