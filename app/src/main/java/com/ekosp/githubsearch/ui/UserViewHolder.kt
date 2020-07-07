package com.ekosp.githubsearch.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekosp.githubsearch.R
import com.ekosp.githubsearch.data.model.GithubUser
import com.ekosp.githubsearch.databinding.ListItemBinding

/**
 * Created by ekosp on 06/07/2020.
 */
class UserViewHolder(view: ListItemBinding) : RecyclerView.ViewHolder(view.root) {

    private val name: TextView = view.titleText
    private val image: ImageView = view.image
    private val type : ImageView = view.type

    private var user: GithubUser? = null

    init {
        // register click listener on the view and open the url link to view repo in browser
        view.root.setOnClickListener {
            user?.htmlUrl?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.root.context.startActivity(intent)
            }
        }
    }

    /**
     * handles displaying the user data per item, or dummy text until it's loaded
     */
    fun bind(user: GithubUser?){
        if (user == null){
            name.text = "Loading"
        }else{
            showUserData(user)
        }
    }

    /**
     * displays the full dat of the user when loaded
     */
    private fun showUserData(user: GithubUser) {
        this.user = user

        name.text = user.login
        Glide.with(itemView.context).load(user.avatarUrl).into(image)
        if (user.type.equals("User")) type.setBackgroundResource(R.drawable.ic_person_blue_24dp)
        else if (user.type.equals("Organization")) type.setBackgroundResource(R.drawable.ic_location_city_blue_24dp)
    }

    companion object {
        /**
         * inflates the view through databinding and instantiates the UserViewHolder
         */
        fun create(parent: ViewGroup): UserViewHolder {
            val inflator = LayoutInflater.from(parent.context)

            val inflatedViewBinding = DataBindingUtil.inflate<ListItemBinding>(inflator,
                R.layout.list_item,
                parent, false)
            return UserViewHolder(inflatedViewBinding)
        }
    }

}