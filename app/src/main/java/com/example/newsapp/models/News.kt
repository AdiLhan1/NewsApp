package com.example.newsapp.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class News {
    @SerializedName("status")
    @Expose
    private val status: String? = null

    @SerializedName("totalResult")
    @Expose
    private val totalResult = 0

    @SerializedName("articles")
    @Expose
    public val articles: List<Article>? = null
}