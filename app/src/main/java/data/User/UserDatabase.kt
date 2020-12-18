package data.User

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDataseClient(context: Context): UserDatabase {
            if (INSTANCE != null) return INSTANCE!!

            kotlin.synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, UserDatabase::class.java, "user_table")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }

    }
}
