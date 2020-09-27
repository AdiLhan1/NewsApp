package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newsapp.data.typeconverter.ArticleModelTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
@TypeConverters(ArticleModelTypeConverter::class)
class News {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResult")
    @Expose
    var totalResult = 0

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
}