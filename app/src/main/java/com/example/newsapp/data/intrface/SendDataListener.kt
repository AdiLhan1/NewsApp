package com.example.newsapp.data.intrface

import com.example.newsapp.models.Article

interface SendDataListener {
    fun passDataCom(
        model: Article
    )
}