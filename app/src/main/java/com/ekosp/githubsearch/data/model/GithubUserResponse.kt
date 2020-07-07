package com.ekosp.githubsearch.data.model

data class GithubUserResponse(
    var incomplete_results: Boolean?,
    var items: List<GithubUser>,
    var total_count: Int
)