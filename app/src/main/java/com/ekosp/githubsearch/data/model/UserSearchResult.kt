package com.ekosp.githubsearch.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by ekosp on 05/07/2020.
 */
data class UserSearchResult(
    val userData: LiveData<PagedList<GithubUser>>,
    val networkErrors: LiveData<String>,
    val isEmpty : LiveData<Boolean>
)