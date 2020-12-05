package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.InternalCoroutinesApi

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
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return view
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel.getUserDetails(myContext)!!.observe(viewLifecycleOwner,{
            name_prof.text = it.name
            address_prof.text = it.address
            password_prof.text = it.password
            email_prof.text = it.email
            phone_number_prof.text = it.phoneNumber
        })
        changeProfil_button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_changeProfil)
        }

    }
}