package com.news.listNews.data

import com.news.readArticle.data.Article

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)