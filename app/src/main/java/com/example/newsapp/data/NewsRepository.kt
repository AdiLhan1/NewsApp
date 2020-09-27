package com.example.newsapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.api.RetrofitClient
import com.example.newsapp.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    companion object {
        private lateinit var newsApi: NewsApi
        fun fetchNewsListData(
            country: String,
            apiKey: String,
            page: Int,
            pageSize: Int
        ): LiveData<News> {
            newsApi = RetrofitClient.create()
            val data = MutableLiveData<News>()

            newsApi.getNews(country, apiKey, page, pageSize).enqueue(object :
                Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    data.value = null
                }

                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    data.value = response.body()
                    Log.e("TAG", "onResponse: ${response.body()}")
                }
            })
            return data
        }
    }
}