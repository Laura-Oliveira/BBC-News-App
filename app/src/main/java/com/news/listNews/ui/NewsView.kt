package com.news.listNews.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.listNews.domain.NewsViewModel
import com.news.readArticle.ui.ArticleView

class NewsView : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels() // ViewModel
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "e549e272c92a425fb12b98713a4dc605"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
        drawable?.alpha = 235 // Valor de 0 a 255 (128 = 50% opacidade)
        recyclerView.background = drawable

        // Configura o RecyclerView
        val newsAdapter = NewsAdapter(emptyList()) { article ->
            // Passa o artigo selecionado para a Activity de detalhes
            val intent = Intent(this, ArticleView::class.java)
            intent.putExtra("article_key", article) // Passa o artigo para a Activity
            startActivity(intent)
        }
        recyclerView.adapter = newsAdapter

        // Observar a lista de artigos na ViewModel
        observeViewModel()

        // Fazer a chamada para buscar as notícias
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    private fun observeViewModel() {
        // Observa a lista de artigos e atualiza o adapter
        newsViewModel.articles.observe(this) { articles ->
            recyclerView.adapter = NewsAdapter(articles) { article ->
                // Passa o artigo selecionado para a Activity de detalhes
                val intent = Intent(this, ArticleView::class.java)
                intent.putExtra("article_key", article)
                startActivity(intent)
            }
        }

        // Observa mensagens de erro e exibe um Toast
        newsViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onPause() {
        super.onPause()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onDestroy() {
        super.onDestroy()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onRestart() {
        super.onRestart()
        newsViewModel.getTopHeadlines("us", apiKey)
    }
}
