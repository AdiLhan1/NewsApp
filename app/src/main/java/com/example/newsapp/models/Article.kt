package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Article : java.io.Serializable {
    @SerializedName("source")
    @Expose
    val source: Source? = null

    @SerializedName("author")
    @Expose
    val author: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null
}