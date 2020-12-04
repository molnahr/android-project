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
@InternalCoroutinesApi
class UserViewModel(application: Application): AndroidViewModel(application) {
    private var readAllData: LiveData<List<User>>
    private var repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User) {
        // egy hatterszalon futtatom
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
//    fun getLoginDetails(context: Context) : LiveData<User>? {
//        readAllData = LoginRepository.getLoginDetails(context)
//        return readAllData
//    }
}