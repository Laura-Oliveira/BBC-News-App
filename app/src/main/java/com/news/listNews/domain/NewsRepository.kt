package com.news.listNews.domain

import com.news.listNews.data.News
import com.news.service.APIService
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: APIService)
{
    suspend fun fetchTopHeadlines(country: String, apiKey: String): Response<News>
    { return apiService.getTopHeadlines(country, apiKey) }
}

