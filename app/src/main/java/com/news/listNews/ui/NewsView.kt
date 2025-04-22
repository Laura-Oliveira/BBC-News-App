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
import com.news.service.Keys
import com.news.service.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsView : AppCompatActivity()
{
    private lateinit var bindingNews: NewsBinding
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Data Binding configuration
        bindingNews = NewsBinding.inflate(layoutInflater)
        setContentView(bindingNews.root)

        //Associate viewModel to layout
        bindingNews.newsViewModel = newsViewModel
        bindingNews.lifecycleOwner = this

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
        newsViewModel.fetchNews()
       // newsViewModel.getTopHeadlines("us", Keys.APIKEY.key)
    }

    private fun observeViewModel()
    {
        newsViewModel.newsState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    // você pode exibir um ProgressBar, se quiser
                }
                is UiState.Success -> {
                    bindingNews.recyclerView.adapter = NewsAdapter(state.data) { article ->
                        navigateToArticle(article)
                    }
                }
                is UiState.Error -> {
                    Toast.makeText(this, "Error NewsView: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    private fun observeViewModel()
//    {
//        //fetch the news data
//        newsViewModel.fetchNews()
//        //newsViewModel.getTopHeadlines("us", Keys.APIKEY.key)
//
//        bindingNews.recyclerView.adapter = NewsAdapter(state.data) { article ->
//                        navigateToArticle(article)
//                    }
//
//        //populate the recyclerView list using data binding and observer
//        newsViewModel.articles.observe(this) { articles ->
//            bindingNews.recyclerView.adapter = NewsAdapter(articles) { article ->
//                val intent = Intent(this, ArticleView::class.java)
//                intent.putExtra("article_key", article)
//                startActivity(intent)
//            }
//        }
//
//        newsViewModel.error.observe(this) { errorMessage ->
//                Toast.makeText(this, "Error NewsView: $errorMessage", Toast.LENGTH_SHORT).show()
//        }
//    }

    //Send the article info to the article screen
    private fun navigateToArticle(article: Article)
    {
        val intent = Intent(this, ArticleView::class.java).apply {
            putExtra("article_key", article)
        }
        startActivity(intent)
    }
}