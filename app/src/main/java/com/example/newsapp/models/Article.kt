package com.example.newsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
class Article : java.io.Serializable {
    @ColumnInfo
    @SerializedName("author")
    @Expose
    var author: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo
    @SerializedName("title")
    @Expose
    var title: String? = null

    @ColumnInfo
    @SerializedName("description")
    @Expose
    var description: String? = null

    @ColumnInfo
    @SerializedName("url")
    @Expose
    var url: String? = null

    @ColumnInfo
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @ColumnInfo
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null

    @ColumnInfo
    @SerializedName("content")
    @Expose
    var content: String? = null
}
