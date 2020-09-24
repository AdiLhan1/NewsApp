package com.example.newsapp.data.`interface`

import com.example.newsapp.models.Article

interface SendDataListener {
    fun passDataCom(
        model: Article
    )
}