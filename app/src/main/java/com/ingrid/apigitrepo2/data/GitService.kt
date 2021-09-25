package com.ingrid.apigitrepo2.data

import com.ingrid.apigitrepo2.model.GitHubResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitService {

    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") query: String?,
        @Query("sort") sort:String?,
        @Query("order") order:String?,
        @Query("page") page:Int?,
        @Query("per_page") per_page:Int?
    ): Response<GitHubResponse>
}