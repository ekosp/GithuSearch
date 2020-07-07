package com.ekosp.githubsearch.data.local

import androidx.paging.DataSource
import com.ekosp.githubsearch.data.model.GithubUser
import java.util.concurrent.Executor

/**
 * Created by ekosp on 06/07/2020.
 */
class GithubLocal(
    private val userDao: UserDao,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of users in the database, on a background thread.
     */
    fun insert(users: List<GithubUser>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            userDao.insert(users)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<User>> from the Dao, based on a User name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name user name
     */
    fun usersByName(name: String): DataSource.Factory<Int, GithubUser> {
        val query = "%${name.replace(' ', '%')}%"
        return userDao.usersByName(query)
    }
}