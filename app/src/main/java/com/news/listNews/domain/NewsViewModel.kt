package com.news.listNews.domain

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
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel()
{
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