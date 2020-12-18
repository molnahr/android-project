package data.User

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// A repository class abstract access to multiple data sources.
// Help us to make cleaner architecture.
class UserRepository(private val userDao: UserDao) {

    // Companion object for static items.
    companion object {
        var userDatabase: UserDatabase? = null
        var user: LiveData<User>? = null
        fun initializeUserDB(context: Context): UserDatabase {
            return UserDatabase.getDataseClient(context)
        }

        // Insert data.
        fun insertData(
            context: Context,
            name: String,
            email: String,
            password: String,
            phoneNumber: String,
            address: String
        ) {

            userDatabase = initializeUserDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val userDetails = User(0, name, email, password, phoneNumber, address)
                userDatabase!!.userDao().InsertData(userDetails)
            }
        }

        // we will use it in the UserViewModel for getUserDetails
        // return the User.
        fun getLoginDetails(context: Context): LiveData<User>? {
            userDatabase = initializeUserDB(context)
            user = userDatabase!!.userDao().getUserDetails()
            return user
        }

        // updating user
        fun updateUser(
            context: Context,
            name: String,
            email: String,
            address: String,
            phone: String,
            password: String
        ) {
            userDatabase = initializeUserDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.userDao().updateUser(name, email, address, phone, password)
            }
        }
    }

}