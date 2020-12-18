package fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_change_profil.*
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 * Change Profil, update user
 * Input check
 */
class ChangeProfilFragment : Fragment() {

    // args what we give from ProfilFragment.
    private val args: ChangeProfilFragmentArgs by navArgs()

    @InternalCoroutinesApi
    private lateinit var mUserViewModel: UserViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_change_profil, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Add new photo. The DataBase did not save it.
        new_photo.setOnClickListener {
            Glide.with(view).load(R.drawable.prof).into(profil_pic)
        }
        // Set parameters.
        name_up.setText(args.name)
        email_up.setText(args.email)
        address_up.setText(args.address)
        phone_number_up.setText(args.password)
        password_up.setText(args.phone)
        save.setOnClickListener {
            updateItem()
        }
    }

    //Update and Item.
    @InternalCoroutinesApi
    fun updateItem() {
        // input check (every member have to fill out)
        if (inputCheck(
                name_up.text.toString(),
                email_up.text.toString(),
                password_up.text.toString(),
                phone_number_up.text.toString(),
                address_up.text.toString()
            )
        ) {
            // Updating user details.
            mUserViewModel.updateUser(
                requireContext(),
                name_up.text.toString(),
                email_up.text.toString(),
                address_up.text.toString(),
                phone_number_up.text.toString(),
                password_up.text.toString()
            )
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_changeProfil_to_profileFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all out fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // input check (every member have to fill out)
    private fun inputCheck(
        name: String,
        email: String,
        password: String,
        phone_number: String,
        address: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(phone_number) || TextUtils.isEmpty(address))
    }
}