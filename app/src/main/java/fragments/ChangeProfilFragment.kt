package fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.restaurantapp.R
import data.User.UserViewModel
import kotlinx.android.synthetic.main.fragment_change_profil.*
import kotlinx.coroutines.InternalCoroutinesApi

class ChangeProfilFragment : Fragment() {


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
        name_up.setText(args.name)
        email_up.setText(args.email)
        address_up.setText(args.address)
        phone_number_up.setText(args.phone)
        password_up.setText(args.password)
        save.setOnClickListener {
            updateItem()
        }
    }
    @InternalCoroutinesApi
    fun updateItem(){

//        if (!inputCheck(name_up,email_up,password_up,phone_number_up,address_up)){
            mUserViewModel.updateUser(requireContext(),name_up.text.toString(),
                email_up.text.toString(),address_up.text.toString(), phone_number_up.text.toString(),
                password_up.text.toString())
            Toast.makeText(requireContext(),"Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_changeProfil_to_profileFragment)
//            }else{
//                Toast.makeText(requireContext(),"Please fill all out fields",Toast.LENGTH_SHORT).show()
//        }
    }
//    private fun inputCheck(
//        name: EditText, email: EditText, password: EditText, phone_number: EditText,
//        address: EditText
//    ):Boolean{
//        return !(TextUtils.isEmpty(name.toString()) && TextUtils.isEmpty(email.toString()) && TextUtils.isEmpty(
//            password.toString()
//        )
//                && TextUtils.isEmpty(phone_number.toString()) && TextUtils.isEmpty(address.toString()))
//    }
}