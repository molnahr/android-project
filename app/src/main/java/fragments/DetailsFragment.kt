package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.model.Supplier
import kotlinx.android.synthetic.main.fragment_details.*


/**
 * A simple [Fragment] subclass.
 * Load details of a restaurant.
 * Sending lng and lat to MapFragment.
 */
class DetailsFragment : Fragment() {
    // Using navArgs()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    // loading all of the data
    fun loadData() {
        name_det.text = Supplier.restaurants[args.arg].name
        address_det.text = Supplier.restaurants[args.arg].address
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

        // Loading the picture.
        Glide.with(view)
            .load(Supplier.restaurants[args.arg].image_url)
            .into(imageView)

        // loading all of the data
        loadData()

        // lat and lng for the google Maps
        val lat = Supplier.restaurants[args.arg].lat.toFloat()
        val lng = Supplier.restaurants[args.arg].lng.toFloat()

        //on click Map_btn we send the lat and lng parameters for the MapsFragment.
        Map_btn.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(
                lat,
                lng
            )
            // Navigate to MapsFragment.
            Navigation.findNavController(view).navigate(action)
        }
    }
}
