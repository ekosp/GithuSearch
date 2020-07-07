package com.ekosp.githubsearch.data.remote

import android.util.Log
import com.ekosp.githubsearch.data.model.GithubUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ekosp on 05/07/2020.
 */

private const val TAG = "GithubService"
private const val IN_QUALIFIER = "in:login"

class GithubApiRequest {

    companion object{
        fun searchUsers(
            service: GithubService,
            query: String,
            page: Int,
            itemsPerPage: Int,
            onSuccess: (users: GithubUserResponse? ) -> Unit,
            onError: (error: String) -> Unit) {

            val apiQuery = query + IN_QUALIFIER

            // make a service call
            service.searchUsers(apiQuery, page, itemsPerPage).enqueue(
                object : Callback<GithubUserResponse> {
                    override fun onFailure(call: Call<GithubUserResponse>?, t: Throwable) {
                        Log.d(TAG, "fail to get data")
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(
                        call: Call<GithubUserResponse>?,
                        response: Response<GithubUserResponse>
                    ) {
                        Log.e(TAG, "got a response ${response.body()}")
                        if (response.isSuccessful) {
                            val users = response.body()
                            onSuccess(users)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }
            )
        }
    }
}