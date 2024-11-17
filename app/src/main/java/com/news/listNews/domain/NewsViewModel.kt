package com.news.listNews.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.data.Article
import com.news.listNews.data.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel()
{
    private val repository = NewsRepository()
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val TAG = "NewsViewModel"

    fun getTopHeadlines(country: String, apiKey: String)
    {
        viewModelScope.launch{
            try
            {
                // Using repository to fetch the articles
                val response = repository.fetchTopHeadlines(country, apiKey)
                val sortedArticles = response.articles.sortedByDescending { it.publishedAt }

                // Log of published dates
                Log.d(TAG, "Dates articles published:")
                sortedArticles.forEach{ article ->
                    Log.d(TAG, "Date: ${article.publishedAt}")
                }

                _articles.value = sortedArticles
            }
            catch (e: Exception)
            {
                _error.value = e.message
                Log.e(TAG, "Error fetching headlines: ${e.message}")
            }
        }
    }
}