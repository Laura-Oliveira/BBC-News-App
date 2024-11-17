package com.news.listNews.domain

import com.news.data.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)