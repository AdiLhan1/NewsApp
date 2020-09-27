package com.example.newsapp.data.api

import com.example.newsapp.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apikey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<News>
}