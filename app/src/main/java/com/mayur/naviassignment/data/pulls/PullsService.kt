package com.mayur.naviassignment.data.pulls

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullsService {

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPulls(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String,
        @Query("page") page: Int = 1,
    ): Response<List<PullRequest>>
}