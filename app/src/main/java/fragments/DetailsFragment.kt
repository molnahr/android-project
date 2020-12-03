package fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurantapp.R
import com.example.restaurantapp.adapter.MyAdapter
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupRecylerview()
//
//        val repository = Repository()
//        val viewModelFactory = DetailsViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
////        val options: HashMap<String,String> = HashMap()
////        options["_sort"] = "id"
////        options["_order"] = "desc"
//        viewModel.getCustomRestaurant(100,"id","desc")
//        viewModel.myCustomRestaurant.observe(viewLifecycleOwner, Observer { response ->
//            if(response.isSuccessful){
//                response.body()?.let { myAdapter.setData(it) }
//            }else{
//                Toast.makeText(context, "HIBA!", Toast.LENGTH_LONG).show();
//            }
//        })
//
//
//    }
//    private fun setupRecylerview(){
//        recyclerView.adapter = myAdapter
//        recyclerView.layoutManager=LinearLayoutManager(activity)
//    }
    }
}