package com.example.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: News)

    @Query("SELECT * FROM news")
    fun getAll(): News

    @Query("DELETE FROM news")
    fun deleteAll()

}