package com.news.readArticle.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.news.R
import com.news.data.Article
import com.squareup.picasso.Picasso

class ArticleView : AppCompatActivity() {
    class ArticleDetailActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.article)

            // Obtém o artigo passado pelo Intent
            val article = intent.getParcelableExtra<Article>("article_key")

            // Exibe as informações do artigo na tela
            findViewById<TextView>(R.id.articleTitle).text = article?.title
            findViewById<TextView>(R.id.articleDescription).text = article?.description
            findViewById<TextView>(R.id.articleContent).text = article?.content
            // Se você quiser exibir uma imagem, use algo como:
            Picasso.get().load(article?.urlToImage).into(findViewById<ImageView>(R.id.articleImage))
        }
    }
}