package com.news.listNews.domain

import com.news.listNews.data.News
import com.news.service.RetrofitInstance

class NewsRepository
{
    suspend fun fetchTopHeadlines(country: String, apiKey: String): News
    { return RetrofitInstance.api.getTopHeadlines(country, apiKey) }
}