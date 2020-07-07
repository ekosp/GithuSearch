package com.ekosp.githubsearch.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ekosp.githubsearch.data.local.GithubLocal
import com.ekosp.githubsearch.data.local.UsersDatabase
import com.ekosp.githubsearch.data.remote.GithubService
import com.ekosp.githubsearch.data.repositories.GithubRepository
import com.ekosp.githubsearch.ui.MainViewModelFactory
import java.util.concurrent.Executors

/**
 * Created by ekosp on 04/07/2020.
 */

object GithubSearchAppInjection{
    /**
     * Creates an instance of [GithubLocal] based on the database DAO.
     */
    private fun provideCache(context: Context): GithubLocal {
        // create Room db
        val database = UsersDatabase.getInstance(context)
        // Dao implementation with single thread sequential executor
        return GithubLocal(database.usersDao(),
            Executors.newSingleThreadExecutor()
        )
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocal]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(
            GithubService.create(),
            provideCache(context)
        )
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     * has instance of the GithubRepository in constructor and context
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return MainViewModelFactory(provideGithubRepository(context))
    }
}