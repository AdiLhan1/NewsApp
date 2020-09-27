package com.example.newsapp.utils

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.db.NewsDatabase

class App : Application() {


    companion object {
        lateinit var database: NewsDatabase
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, NewsDatabase::class.java, "news.db")
            .allowMainThreadQueries()
            .build()
    }

    fun getInstance(): App {
        return instance
    }

    fun getDatabase(): NewsDatabase {
        return database
    }
}