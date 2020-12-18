package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.restaurantapp.R
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 * Show user details.
 * Send data to ChangeProfilFragment
 */
class ProfileFragment : Fragment() {
    @InternalCoroutinesApi
    private lateinit var mUserViewModel: UserViewModel
    lateinit var myContext: Context

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        myContext = this.requireContext()

        // Using ViewModelProvider
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return view
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Get details and add values.
        mUserViewModel.getUserDetails(myContext)!!.observe(viewLifecycleOwner, {
            if (it != null) {
                name_prof.text = it.name
                address_prof.text = it.address
                password_prof.text = it.password
                email_prof.text = it.email
                phone_number_prof.text = it.phoneNumber
            }
        })
        // changeProfil_button button pressed
        changeProfil_button.setOnClickListener {
            // Adding arguments to the action and destination.
            val action = ProfileFragmentDirections.actionProfileFragmentToChangeProfil(
                name_prof.text.toString(),
                address_prof.text.toString(),
                password_prof.text.toString(),
                email_prof.text.toString(),
                phone_number_prof.text.toString()
            )
            // Navigation to  ChangeProfileFragment with navController and send arguments.
            Navigation.findNavController(view).navigate(action)
        }

    }
}