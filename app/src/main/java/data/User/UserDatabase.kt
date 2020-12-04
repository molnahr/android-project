package data.User

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        //singeltonna tesszuk a room adatbazisunkat
        @Volatile
        private var INSTANCE: UserDatabase? = null

        //ha nem letezik az INSTANCE csinalunk egyet, s ha mar letezik, akkor a meglevot hasznaljuk
        @InternalCoroutinesApi
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
//            a block le lesz vedve a konkurens szalak ellen
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_table"
                ).build()
                INSTANCE = instance
                return instance
                }
            }
        }
    }
