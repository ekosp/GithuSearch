package com.ekosp.githubsearch.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekosp.githubsearch.data.model.GithubUser

/**
 * Created by ekosp on 05/07/2020.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<GithubUser>)

    // Do a similar query as the search API:
    // Look for users that contain the query string in the name login
    @Query("SELECT * FROM users WHERE (login LIKE :queryString)")
    fun usersByName(queryString: String): DataSource.Factory<Int, GithubUser>
}