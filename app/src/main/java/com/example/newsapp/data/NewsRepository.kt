package com.example.newsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.RetrofitClient
import com.example.newsapp.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository() {

    companion object {
        private lateinit var newsApi: NewsApi
        fun fetchNewsListData(country: String, apiKey: String): LiveData<News> {
            newsApi = RetrofitClient.create()
            val data = MutableLiveData<News>()

            newsApi.getNews(country, apiKey).enqueue(object :
                Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    data.value = null
                }

                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    data.value = response.body()
                }
            })
            return data
        }

    }

//    override fun getNews(
//        country: String, apiKey: String, callback: INewsApiClient.NewsCallback
//    ) {
//        iNewsApiClient.getNews(country, apiKey, object : INewsApiClient.NewsCallback {
//            override fun onSuccess(result: News?) {
//                callback.onSuccess(result)
//            }
//
//            override fun onFailure(e: Exception?) {
//                callback.onFailure(e)
//            }
//
//        })
//    }
}