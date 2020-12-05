package data.User

import android.content.Context
import androidx.lifecycle.LiveData
import data.User.User
import data.User.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// A repository class abstract acces to multiple data sources.
// clean arhitecure
class UserRepository(private val userDao: UserDao) {

    companion object {

        var userDatabase: UserDatabase? = null

        var user: LiveData<User>? = null

        fun initializeUserDB(context: Context): UserDatabase {
            return UserDatabase.getDataseClient(context)
        }

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

        fun getLoginDetails(context: Context): LiveData<User>? {

            userDatabase = initializeUserDB(context)

            user = userDatabase!!.userDao().getUserDetails()

            return user
        }
    }

}