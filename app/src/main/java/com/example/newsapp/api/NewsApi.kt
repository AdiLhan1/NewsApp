package com.example.newsapp.api

import com.example.newsapp.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apikey: String
    ): Call<News>
}