package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.restaurantapp.R
import data.User.User
import data.User.UserDao
import data.User.UserDatabase
import kotlinx.android.synthetic.main.fragment_login.*


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

//        navController = Navigation.findNavController(view)
////         view.findViewById<Button>(R.id.login).setOnClickListener{
////             var emailText: String = email_log.text.toString()
////             var passwordText: String = password_log.text.toString()
////             if (passwordText.isEmpty() || emailText.isEmpty()){
////                 Toast.makeText(requireContext(),"Fill all Fields!",Toast.LENGTH_SHORT).show()
////             }else{
////                 var userDatabase: UserDatabase = UserDatabase.getDataseClient(requireContext())
////                 var userDao: UserDao = userDatabase.userDao()
////                 var user = userDao.login(emailText,passwordText)
////             }
//         }





        view.findViewById<Button>(R.id.register).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.register ->  navController!!.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}