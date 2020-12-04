package data.User

import androidx.lifecycle.LiveData
import data.User.User
import data.User.UserDao

// A repository class abstract acces to multiple data sources.
// clean arhitecure
class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}