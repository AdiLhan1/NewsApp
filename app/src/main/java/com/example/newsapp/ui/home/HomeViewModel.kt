package com.example.newsapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.models.News
import com.example.newsapp.utils.App

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var db = App().getInstance().getDatabase()

    fun getNewsListData(country: String, apiKey: String, page: Int, pageSize: Int): LiveData<News> {
        return NewsRepository.fetchNewsListData(country, apiKey, page, pageSize)
    }

    fun insertNewsData(model: News) {
        db.newsDao().insert(model)
    }

    fun getNewsFromDB(): News {
        return db.newsDao().getAll()
    }

    fun deleteAllNews() {
        db.newsDao().deleteAll()
    }
}