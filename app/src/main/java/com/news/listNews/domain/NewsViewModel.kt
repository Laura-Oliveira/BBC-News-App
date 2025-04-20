package com.news.listNews.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.readArticle.data.Article
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
        viewModelScope.launch {
            try
            {
                // Using repository to fetch the articles
                val response = repository.fetchTopHeadlines(country, apiKey)
                val news = response.body()

                if (response.isSuccessful)
                {
                    val sortedArticles = news?.articles?.sortedByDescending { it.publishedAt }

                    if (!sortedArticles.isNullOrEmpty())
                    {
                        Log.d(TAG, "Dates articles published:")
                        sortedArticles.forEach { article ->
                            Log.d(TAG, "Date: ${article.publishedAt}")
                        }

                        _articles.value = sortedArticles
                    }
                    else {
                        _error.value = "No articles found."
                    }
                }
                else
                {
                    _error.value = "API Error: ${response.code()} - ${response.message()}"
                }
            }
            catch (e: Exception)
            {
                _error.value = "Exception: ${e.message}"
                Log.e(TAG, "Error fetching headlines", e)
            }
        }
    }
}