package com.news.listNews.domain

import com.news.listNews.data.News
import com.news.service.RetrofitInstance
import retrofit2.Response

class NewsRepository
{
    suspend fun fetchTopHeadlines(country: String, apiKey: String): Response<News>
    { return RetrofitInstance.api.getTopHeadlines(country, apiKey) }
}