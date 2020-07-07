package com.ekosp.githubsearch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ekosp.githubsearch.data.model.GithubUser
/**
 * Created by ekosp on 06/07/2020.
 */

/**
 * Database schema that holds the list of users.
 */
@Database(
    entities = [GithubUser::class],
    version = 2,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase(){

    abstract fun usersDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        /**
         * build our room DB
         */
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UsersDatabase::class.java, "github_search.db")
                .build()
    }

}