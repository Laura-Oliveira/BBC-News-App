package com.news.listNews.domain

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)