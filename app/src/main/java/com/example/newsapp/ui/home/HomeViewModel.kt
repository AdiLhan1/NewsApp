package com.example.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.models.News

class HomeViewModel : ViewModel() {
    fun getNewsListData(country: String, apiKey: String): LiveData<News> {
        return NewsRepository.fetchNewsListData(country, apiKey)
    }
}