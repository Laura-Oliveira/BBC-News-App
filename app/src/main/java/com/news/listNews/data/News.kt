package com.news.listNews.data

import android.os.Parcelable
import com.news.readArticle.data.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
): Parcelable