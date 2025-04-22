package com.news.listNews.domain

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.readArticle.data.Article
import com.news.service.Keys
import com.news.service.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableLiveData<UiState<List<Article>>>()
    val newsState: LiveData<UiState<List<Article>>> = _newsState

    init {
        fetchNews()
    }

    fun fetchNews()
    {
        viewModelScope.launch {
            _newsState.value = UiState.Loading
            try {
                val response = repository.fetchTopHeadlines(Keys.COUNTRY.key, Keys.APIKEY.key)
                if (response.isSuccessful && response.body() != null) {
                    _newsState.value = UiState.Success(response.body()!!.articles)
                } else {
                    _newsState.value = UiState.Error("Erro ao carregar notícias")
                }
            } catch (e: Exception) {
                _newsState.value = UiState.Error("Erro: ${e.localizedMessage}")
            }
        }
    }
}


//class NewsViewModel : ViewModel()
//{
//    private val repository = NewsRepository()
//    private val _articles = MutableLiveData<List<Article>>()
//    val articles: LiveData<List<Article>> get() = _articles
//
//    private val _error = MutableLiveData<String>()
//    val error: LiveData<String> get() = _error
//
//    private val TAG = "NewsViewModel"
//
//    fun getTopHeadlines(country: String, apiKey: String)
//    {
//        viewModelScope.launch {
//            try
//            {
//                // Using repository to fetch the articles
//                val response = repository.fetchTopHeadlines(country, apiKey)
//                val news = response.body()
//
//                if (response.isSuccessful)
//                {
//                    val sortedArticles = news?.articles?.sortedByDescending { it.publishedAt }
//
//                    if (!sortedArticles.isNullOrEmpty())
//                    {
//                        Log.d(TAG, "Dates articles published:")
//                        sortedArticles.forEach { article ->
//                            Log.d(TAG, "Date: ${article.publishedAt}")
//                        }
//
//                        _articles.value = sortedArticles
//                    }
//                    else {
//                        _error.value = "No articles found."
//                    }
//                }
//                else
//                {
//                    _error.value = "API Error: ${response.code()} - ${response.message()}"
//                }
//            }
//            catch (e: Exception)
//            {
//                _error.value = "Exception: ${e.message}"
//                Log.e(TAG, "Error fetching headlines", e)
//            }
//        }
//    }
//}