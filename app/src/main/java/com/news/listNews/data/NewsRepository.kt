package com.news.listNews.data

import com.news.listNews.domain.NewsResponse

class NewsRepository {
    suspend fun fetchTopHeadlines(country: String, apiKey: String): NewsResponse {
        return RetrofitInstance.api.getTopHeadlines(country, apiKey)
    }
}