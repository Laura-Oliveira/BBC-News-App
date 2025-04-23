package com.news.unitTest

import com.news.listNews.domain.NewsRepository
import com.news.listNews.data.News
import com.news.service.APIService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response

class NewsRepositoryUnit
{
    private val apiService: APIService = mock(APIService::class.java)
    private val repository = NewsRepository(apiService)

    @Test
    fun testFetchTopHeadlines(): Unit = runBlocking {
        // Mock the response from APIService
        val newsResponse = News(
            status = "ok",
            totalResults = 10,
            articles = emptyList()
        )
        val response = Response.success(newsResponse)

        Mockito.`when`(apiService.getTopHeadlines("us", "apiKey")).thenReturn(response)

        // Call the repository method
        val result = repository.fetchTopHeadlines("us", "apiKey")

        // Assert the result
        assert(result.isSuccessful)
        assert(result.body()?.status == "ok")
        assert(result.body()?.totalResults == 0)

        // Verify that the API service was called with correct parameters
        verify(apiService).getTopHeadlines("us", "apiKey")
    }

    @Test
    fun testFetchTopHeadlinesEmptyResponse(): Unit = runBlocking {
        // Mock the response from APIService with empty articles
        val newsResponse = News(
            status = "ok",
            totalResults = 0,
            articles = emptyList()
        )
        val response = Response.success(newsResponse)

        Mockito.`when`(apiService.getTopHeadlines("us", "apiKey")).thenReturn(response)

        // Call the repository method
        val result = repository.fetchTopHeadlines("us", "apiKey")

        // Assert the result
        assert(result.isSuccessful)
        assert(result.body()?.status == "ok")
        assert(result.body()?.totalResults == 0) 

        // Verify that the API service was called with correct parameters
        verify(apiService).getTopHeadlines("us", "apiKey")
    }

    @Test
    fun testFetchTopHeadlinesError(): Unit = runBlocking {
        // Mock the response from APIService to simulate an error
        Mockito.`when`(apiService.getTopHeadlines("us", "apiKey")).thenThrow(RuntimeException("API error"))

        // Call the repository method and catch the exception
        try {
            repository.fetchTopHeadlines("us", "apiKey")
            assert(false) // Fail if no exception is thrown
        } catch (e: Exception) {
            assert(e is RuntimeException)
            assert(e.message == "API error")
        }

        // Verify that the API service was called
        verify(apiService).getTopHeadlines("us", "apiKey")
    }
}