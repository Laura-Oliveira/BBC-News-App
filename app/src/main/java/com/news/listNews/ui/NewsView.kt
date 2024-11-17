//package com.news.listNews.ui
//
//import android.content.Intent
//import android.graphics.Color
//import android.graphics.drawable.GradientDrawable
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.news.R
//import com.news.data.Article
//import com.news.listNews.data.RetrofitInstance
//import com.news.databinding.MainBinding
//import com.news.listNews.domain.NewsViewModel
//import com.news.readArticle.ui.ArticleView
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class NewsView : AppCompatActivity() {
//
//    private val newsViewModel: NewsViewModel by viewModels()
//    private lateinit var recyclerView: RecyclerView
//    private val apiKey = "e549e272c92a425fb12b98713a4dc605"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.news)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Supondo que você tenha uma lista de artigos chamada 'articles'
//        val newsAdapter = NewsAdapter(articles) { article ->
//            // Passa o artigo selecionado para a Activity de detalhes
//            val intent = Intent(this, ArticleView::class.java)
//            intent.putExtra("article_key", article) // Passa o artigo para a Activity
//            startActivity(intent)
//        }
//
//// Configurando o RecyclerView
//        recyclerView.adapter = newsAdapter
//
//        recyclerView.adapter = newsAdapter
//
//        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
//        drawable?.alpha = 180 // Valor de 0 a 255 (128 = 50% opacidade)
//        recyclerView.background = drawable
//
////        val roundedBackground = GradientDrawable().apply {
////            shape = GradientDrawable.RECTANGLE
////            cornerRadius = 16f // Valor em pixels; use `resources.getDimension(R.dimen.some_value)` para dimensões em dp
////         //   setColor(Color.parseColor("#80FFFFFF")) // Cor com opacidade
////        }
////        recyclerView.background = roundedBackground
//
//        observeViewModel()
//        newsViewModel.getTopHeadlines("us", apiKey)
//    }
//
//    private fun observeViewModel() {
//        newsViewModel.articles.observe(this) { articles ->
//            recyclerView.adapter = NewsAdapter(articles)
//        }
//
//        newsViewModel.error.observe(this) { errorMessage ->
//            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
//        }
//    }
//
////    private lateinit var recyclerView: RecyclerView
////    private val apiKey = "e549e272c92a425fb12b98713a4dc605"
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.news)
////
////        recyclerView = findViewById(R.id.recyclerView)
////        recyclerView.layoutManager = LinearLayoutManager(this)
////
////        fetchNews()
////    }
//
//
////    private fun fetchNews() {
////        CoroutineScope(Dispatchers.IO).launch {
////            try {
////                val response = RetrofitInstance.api.getTopHeadlines("us", apiKey)
////                val sortedArticles = response.articles.sortedByDescending { it.publishedAt }
////
////                runOnUiThread {
////                    recyclerView.adapter = NewsAdapter(sortedArticles)
////                }
////            } catch (e: Exception) {
////                runOnUiThread {
////                    Toast.makeText(this@NewsView, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
////                }
////            }
////        }
//
//    override fun onStart() {
//        super.onStart()
//        newsViewModel.getTopHeadlines("us", apiKey)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        newsViewModel.getTopHeadlines("us", apiKey)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        newsViewModel.getTopHeadlines("us", apiKey)
//    }
//}


package com.news.listNews.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.listNews.data.NewsRepository
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
}
