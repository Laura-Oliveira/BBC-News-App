package com.news.listNews.domain

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String
)