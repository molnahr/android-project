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
import com.example.restaurantapp.R
import data.User.User
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.coroutines.InternalCoroutinesApi


class RegisterFragment : Fragment() {


    @InternalCoroutinesApi
    private lateinit var mUserViewModel: UserViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.register.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    @InternalCoroutinesApi
    private fun insertDataToDatabase() {
        val email = email_reg.text.toString()
        val name = name_reg.text.toString()
        val password = password_reg.text.toString()

        if(inputCheck(name,email,password)){
            val user = User(0,name, email,password)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Sikeres!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registerFragment_to_listOfRestaurantsFragment)
        }else{
            Toast.makeText(requireContext(), "Toltse ki az osszes mezot",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, email: String, password: String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }

}