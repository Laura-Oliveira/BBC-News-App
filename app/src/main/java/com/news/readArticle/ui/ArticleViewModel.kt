package com.news.readArticle.ui

import com.news.data.Article

class ArticleViewModel {
    // A propriedade que armazena o artigo carregado
    private var _article: Article? = null
    val article: Article?
        get() = _article

    // Método para configurar o artigo
    fun setArticle(article: Article) {
        _article = article
    }
}