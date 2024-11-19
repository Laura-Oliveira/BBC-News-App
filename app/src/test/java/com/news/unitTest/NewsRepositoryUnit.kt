package com.news.unitTest

import com.news.listNews.domain.NewsRepository
import com.news.listNews.data.News
import com.news.service.APIService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class NewsRepositoryUnit {

    private val apiService: APIService = mock(APIService::class.java)
    private val repository = NewsRepository()

    @Test
    fun testFetchTopHeadlines() = runBlocking {
        // Mock the response from APIService
        val newsResponse = News(
            status = "ok",
            totalResults = 10,
            articles = emptyList()
        )
        Mockito.`when`(apiService.getTopHeadlines("us", "apiKey")).thenReturn(newsResponse)

        // Call the repository method
        val result = repository.fetchTopHeadlines("us", "apiKey")

        // Assert the result
        assert(result.status == "ok")
        assert(result.totalResults == 10)
    }
}