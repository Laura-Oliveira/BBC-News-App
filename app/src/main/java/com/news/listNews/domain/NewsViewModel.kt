package com.news.listNews.domain
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.data.RetrofitInstance
import com.news.listNews.data.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class NewsViewModel : ViewModel() {
//
//    private val _articles = MutableLiveData<List<Article>>()
//    val articles: LiveData<List<Article>> get() = _articles
//
//    private val _errorMessage = MutableLiveData<String?>()
//    val errorMessage: LiveData<String?> get() = _errorMessage
//
//    private val apiKey = "e549e272c92a425fb12b98713a4dc605"
//
//    fun fetchNews() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = RetrofitInstance.api.getTopHeadlines("us", apiKey)
//                val sortedArticles = response.articles.sortedByDescending { it.publishedAt }
//                _articles.postValue(sortedArticles)
//            } catch (e: Exception) {
//                _errorMessage.postValue(e.message)
//            }
//        }
//    }


    class NewsViewModel : ViewModel() {
        private val repository = NewsRepository()
        private val _articles = MutableLiveData<List<Article>>()
        val articles: LiveData<List<Article>> get() = _articles

        private val _error = MutableLiveData<String>()
        val error: LiveData<String> get() = _error

        fun getTopHeadlines(country: String, apiKey: String) {
            viewModelScope.launch {
                try {
                    val response = repository.fetchTopHeadlines(country, apiKey)
                    _articles.value = response.articles.sortedByDescending { it.publishedAt }
                } catch (e: Exception) {
                    _error.value = e.message
                }
            }
        }
    }
//}
