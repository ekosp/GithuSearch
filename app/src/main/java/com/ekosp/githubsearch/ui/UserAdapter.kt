package com.ekosp.githubsearch.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ekosp.githubsearch.data.model.GithubUser

/**
 * Created by ekosp on 04/07/2020.
 */

/**
 * Adapter for the list of users.
 */
class UserAdapter : PagedListAdapter<GithubUser, RecyclerView.ViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userItem = getItem(position)
        if (userItem != null) (holder as UserViewHolder).bind(userItem)
    }

    /**
     * diffUtils implementation for comparator
     */
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GithubUser>() {
            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
                oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
                oldItem == newItem
        }
    }
}