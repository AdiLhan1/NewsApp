package com.example.newsapp.data.typeconverter

import androidx.room.TypeConverter
import com.example.newsapp.models.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ArticleModelTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun toArticle(objects: List<Article>): String {
        return gson.toJson(objects)
    }

    @TypeConverter
    fun fromArticle(data: String?): List<Article> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(data, listType)
    }
}
