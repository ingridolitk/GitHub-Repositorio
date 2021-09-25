package com.ingrid.apigitrepo2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    const val BASE_URL:String="https://api.github.com/search/"

    //retrofit singleton: Instance of the Retrofit to always call the exact same Instance
    val retrofitBuilder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: GitService by lazy{
        retrofitBuilder
            .build()
            .create(GitService::class.java)
    }
}