package data.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // Insert User to database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(user: User)

    // return the all of the users order by Desc.
    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun getUserDetails(): LiveData<User>

    // update users.
    @Query("UPDATE user_table SET name = :name, address = :address, email = :email,  phoneNumber = :phone, password =:password")
    fun updateUser(name: String, email: String, address: String, phone: String, password: String)

}