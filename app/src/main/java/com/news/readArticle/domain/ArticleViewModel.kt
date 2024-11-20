package com.news.readArticle.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.readArticle.data.Article

class ArticleViewModel : ViewModel()
{
    private val _article = MutableLiveData<Article?>()
    val article: LiveData<Article?> get() = _article

    fun setArticle(article: Article)
    { _article.value = article }
}