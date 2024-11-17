package com.news.listNews.data

import com.news.listNews.domain.NewsResponse
import com.news.service.RetrofitInstance

class NewsRepository
{
    suspend fun fetchTopHeadlines(country: String, apiKey: String): NewsResponse
    { return RetrofitInstance.api.getTopHeadlines(country, apiKey) }
}