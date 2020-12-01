package fragments

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapp.DetailsViewModel
import com.example.restaurantapp.DetailsViewModelFactory
import com.example.restaurantapp.R
import com.example.restaurantapp.adapter.MyAdapter
import com.example.restaurantapp.repository.Repository
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = DetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailsViewModel::class.java)
        viewModel.gerRestaurant()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            Log.d("Response", response.address)
            Log.d("Response", response.area)
            Log.d("Response", response.country)
            Log.d("Response", response.city)
            Log.d("Response", response.name)
            Log.d("Response", response.postal_code)
            Log.d("Response", response.state)
            Log.d("Response", response.id.toString())
        })
    }

}