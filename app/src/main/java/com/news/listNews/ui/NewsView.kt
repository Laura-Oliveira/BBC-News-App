package com.news.listNews.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.R
import com.news.databinding.NewsBinding
import com.news.listNews.domain.NewsViewModel
import com.news.readArticle.data.Article
import com.news.readArticle.ui.ArticleView

class NewsView : AppCompatActivity()
{

    private lateinit var bindingNews: NewsBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private val apiKey = "e549e272c92a425fb12b98713a4dc605"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Data Binding configuration
        bindingNews = NewsBinding.inflate(layoutInflater)
        setContentView(bindingNews.root)

        //Associate viewModel to layout
        bindingNews.newsViewModel = newsViewModel
        bindingNews.lifecycleOwner = this

      //  swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
        drawable?.alpha = 150
        bindingNews.recyclerView.background = drawable

        // Configurar RecyclerView
        val newsAdapter = NewsAdapter(emptyList()) { article ->
            navigateToArticle(article)
        }
        bindingNews.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsView)
        }

        observeViewModel()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    private fun observeViewModel()
    {
        newsViewModel.articles.observe(this) { articles ->
            bindingNews.recyclerView.adapter = NewsAdapter(articles) { article ->
                val intent = Intent(this, ArticleView::class.java)
                intent.putExtra("article_key", article)
                startActivity(intent)
            }

            newsViewModel.error.observe(this) { errorMessage ->
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }

//        newsViewModel.error.observe(this) { errorMessage ->
//            swipeRefreshLayout.isRefreshing = false // Para o indicador de carregamento
//            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
//        }

        newsViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    //Send the article info to the article screen
    private fun navigateToArticle(article: Article)
    {
        val intent = Intent(this, ArticleView::class.java).apply {
            putExtra("article_key", article)
        }

        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        newsViewModel.getTopHeadlines("us", apiKey)
        bindingNews.recyclerView
    }

    override fun onStart()
    {
        super.onStart()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onResume()
    {
        super.onResume()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onPause()
    {
        super.onPause()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    override fun onRestart()
    {
        super.onRestart()
        newsViewModel.getTopHeadlines("us", apiKey)
    }
}