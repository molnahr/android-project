package data.User

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import data.User.User
import data.User.UserDatabase
import data.User.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

// The ViewModels role is to provide data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and th UI

class UserViewModel(application: Application): AndroidViewModel(application) {
    var liveDataUser: LiveData<User>? = null

    fun insertData( context: Context,
                    name: String,
                    email: String,
                    password: String,
                    phoneNumber: String,
                    address: String) {
        UserRepository.insertData(context, name, email,password,phoneNumber,address)
    }

    fun getUserDetails(context: Context) : LiveData<User>? {
        liveDataUser = UserRepository.getLoginDetails(context)
        return liveDataUser
    }

//    fun updateUser(user:User){
//        viewModelScope.launch (Dispatchers.IO){
//            UserRepository.value?
//        }
//    }

}