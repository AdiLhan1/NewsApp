package com.example.newsapp.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Source {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}