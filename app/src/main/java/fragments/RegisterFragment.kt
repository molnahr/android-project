package fragments

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 * Registration
 * Input check
 * Password show-hide
 */
class RegisterFragment : Fragment() {

    private var mIsShowPass = false
    private lateinit var mUserViewModel: UserViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Add ViewModelProvider to UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Hide and show password
        view.isShowHidePass.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
        // Resgister button pressed
        view.register.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    /**
     * Insert data to database if the input check is oky.
     */
    @InternalCoroutinesApi
    private fun insertDataToDatabase() {
        val email = email_reg.text.toString()
        val name = name_reg.text.toString()
        val password = password_reg.text.toString()
        val phoneNumber = phone_number_reg.text.toString()
        val address = address_reg.text.toString()

        // inputCheck for every filed
        if (inputCheck(name, email, password, phoneNumber, address)) {
            // Add Data to Database
            context?.let { it1 ->
                mUserViewModel.insertData(
                    it1,
                    name,
                    email,
                    password,
                    phoneNumber,
                    address
                )
            }
            Toast.makeText(requireContext(), "Successfully Registration!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registerFragment_to_listOfRestaurantsFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
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

    // Password show and hide.
    fun showPassword(isShow: Boolean) {
        if (isShow) {
            // To show password
            password_reg.transformationMethod = HideReturnsTransformationMethod.getInstance()
            isShowHidePass.setImageResource(R.drawable.ic_hide_password)
        } else {
            // To hide the password
            password_reg.transformationMethod = PasswordTransformationMethod.getInstance()
            isShowHidePass.setImageResource(R.drawable.ic_show_password)
        }
        // This line of code to put pointer at the end of the password String
        password_reg.setSelection(password_reg.text.toString().length)
    }
}