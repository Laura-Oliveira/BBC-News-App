package com.news.listNews.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.listNews.domain.NewsViewModel
import com.news.readArticle.ui.ArticleView

class NewsView : AppCompatActivity()
{
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "e549e272c92a425fb12b98713a4dc605"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
        drawable?.alpha = 222 // Valor de 0 a 255 (128 = 50% opacidade)
        recyclerView.background = drawable

        // Configura o RecyclerView
        val newsAdapter = NewsAdapter(emptyList()) { article ->
            // Send the selected article obj info to the next screen
            val intent = Intent(this, ArticleView::class.java)
            intent.putExtra("article_key", article)
            Log.d("Article", "URL to Image: ${article.urlToImage}")
            startActivity(intent)
        }
        recyclerView.adapter = newsAdapter

        observeViewModel()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    private fun observeViewModel()
    {
        newsViewModel.articles.observe(this) { articles ->
            recyclerView.adapter = NewsAdapter(articles) { article ->
                Log.d("Article [NewsView]", "URL to Image: ${article.urlToImage}")
                val intent = Intent(this, ArticleView::class.java)
                intent.putExtra("article_key", article)
                Log.d("Article OBJ [NewsView]", "${article}")
                startActivity(intent)
            }
        }

        newsViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        newsViewModel.getTopHeadlines("us", apiKey)
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
