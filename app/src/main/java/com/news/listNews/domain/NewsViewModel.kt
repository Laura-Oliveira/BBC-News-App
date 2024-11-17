package com.news.listNews.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.listNews.data.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val TAG = "NewsViewModel"

    //Ordering by newest article first
    fun getTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchTopHeadlines(country, apiKey)
                val sortedArticles = response.articles.sortedByDescending { it.publishedAt }

                // Log das datas de publicação
                Log.d(TAG, "Datas de publicação dos artigos:")
                sortedArticles.forEach { article ->
                    Log.d(TAG, "Data: ${article.publishedAt}")
                }

                _articles.value = sortedArticles
            } catch (e: Exception) {
                _error.value = e.message
                Log.e(TAG, "Erro ao buscar manchetes: ${e.message}")
            }
        }
    }
}

