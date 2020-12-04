package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.Injection.Injection
import com.example.restaurantapp.data.RestaurantSearchResult
import com.example.restaurantapp.databinding.FragmentListOfRestaurantsBinding
import kotlinx.android.synthetic.main.fragment_list_of_restaurants.*


class ListOfRestaurantsFragment : Fragment() {

    private lateinit var binding: FragmentListOfRestaurantsBinding
    private lateinit var viewModel: SearchRestaurantsViewModel
    private val adapter = RestaurantAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListOfRestaurantsBinding.inflate(layoutInflater)
        // get the view model
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(SearchRestaurantsViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerViewList.addItemDecoration(decoration)

        setupScrollListener()
        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        if (viewModel.RestaurantResult.value == null) {
            viewModel.searchRestaurant(query)
        }
        initSearch(query)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, binding.searchRetaurant.text.trim().toString())
    }

    private fun initAdapter() {
        binding.recyclerViewList.adapter = adapter
        viewModel.RestaurantResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RestaurantSearchResult.Success -> {
                    adapter.submitList(result.data)
                }
                is RestaurantSearchResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "\uD83D\uDE28 Wooops $result.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initSearch(query: String) {
        binding.searchRetaurant.setText(query)

        binding.searchRetaurant.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRestaurantListFromInput()
                true
            } else {
                false
            }
        }
        binding.searchRetaurant.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRestaurantListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRestaurantListFromInput() {
        binding.searchRetaurant.text.trim().let {
            if (it.isNotEmpty()) {
                recycler_view_list.scrollToPosition(0)
                viewModel.searchRestaurant(it.toString())
            }
        }
    }


    //ok
    private fun setupScrollListener() {
        val layoutManager = binding.recyclerViewList.layoutManager as LinearLayoutManager
        //talan nem
        binding.recyclerViewList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "US"
    }
}
