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

    @Query("UPDATE user_table SET name = :name, address = :address, email = :email,  phoneNumber = :phone, password =:password")
    fun updateUser(name: String, email: String, address: String, phone: String, password: String)

//    @Query("Select * from user_table where email=(:email) and password=(:password)")
//    fun login(email: String, password: String)

}