package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.restaurantapp.R
import com.example.restaurantapp.model.Supplier
import kotlinx.android.synthetic.main.fragment_details.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    fun loadData() {
        name_det.text = Supplier.restaurants[args.arg].name
        address_det.text = Supplier.restaurants[args.arg].address
//        Glide.with(this)
//            .load(Supplier.restaurants[args.position].image)
//            .placeholder(R.drawable.ic_restaurant_background)
//            .into(detail_rest_img)
        city_det.text = Supplier.restaurants[args.arg].city
        state_det.text = Supplier.restaurants[args.arg].state
        area_det.text = Supplier.restaurants[args.arg].area
        postal_code_det.text = Supplier.restaurants[args.arg].postal_code.toString()
        country_det.text = Supplier.restaurants[args.arg].country
        phone_det.text = Supplier.restaurants[args.arg].phone
        price_det.text = Supplier.restaurants[args.arg].price.toString()
        reserve_url_det.text = Supplier.restaurants[args.arg].reserve_url
        mobile_reserve_url_det.text = Supplier.restaurants[args.arg].mobile_reserve_url

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }
}
