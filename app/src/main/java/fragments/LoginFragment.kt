package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.restaurantapp.R


class LoginFragment : Fragment(),View.OnClickListener {

    var navController: NavController? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.login).setOnClickListener(this)
        view.findViewById<Button>(R.id.register).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.login -> navController!!.navigate(R.id.action_loginFragment_to_listOfRestaurantsFragment)
            R.id.register ->  navController!!.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}