package com.example.restaurantapp.Injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.api.SimpleApi
import com.example.restaurantapp.data.RestaurantRepository
import com.example.restaurantapp.ui.ViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(): RestaurantRepository {
        return RestaurantRepository(SimpleApi.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}