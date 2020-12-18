package data.User

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


// The ViewModels role is to provide data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and th UI
class UserViewModel(application: Application) : AndroidViewModel(application) {
    var liveDataUser: LiveData<User>? = null

    // Insert data to database.
    fun insertData(
        context: Context,
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        address: String
    ) {
        UserRepository.insertData(context, name, email, password, phoneNumber, address)
    }

    // Get User details.
    fun getUserDetails(context: Context): LiveData<User>? {
        liveDataUser = UserRepository.getLoginDetails(context)
        return liveDataUser
    }

    // Update User.
    fun updateUser(
        context: Context,
        name: String,
        email: String,
        address: String,
        phone: String,
        password: String
    ) {
        UserRepository.updateUser(context, name, email, address, phone, password)
    }

}