package com.news.readArticle.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String?,
    val description: String?,
    val publishedAt: String?,
    val content: String?,
    val urlToImage: String?
) : Parcelable