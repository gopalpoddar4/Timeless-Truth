package com.gopalpoddar4.timelesstruth.API

import com.gopalpoddar4.timelesstruth.Model.NewsResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterfcae {

    //To fetch all news and search
    @GET("everything")
    suspend fun getEveryNews(
        @Query("q") q: String,
        @Query("language") language:String,
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey:String
    ): NewsResponce
}