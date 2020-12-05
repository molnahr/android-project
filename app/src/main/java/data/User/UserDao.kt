package data.User

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import data.User.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(user: User)

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun getUserDetails() : LiveData<User>

//    @Query("Select * from user_table where email=(:email) and password=(:password)")
//    fun login(email: String, password: String)

}