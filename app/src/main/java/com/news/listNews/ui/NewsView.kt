package com.news.listNews.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.data.RetrofitInstance
import com.news.databinding.MainBinding
import com.news.listNews.domain.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsView : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "e549e272c92a425fb12b98713a4dc605"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
        drawable?.alpha = 220 // Valor de 0 a 255 (128 = 50% opacidade)
        recyclerView.background = drawable

//        val roundedBackground = GradientDrawable().apply {
//            shape = GradientDrawable.RECTANGLE
//            cornerRadius = 16f // Valor em pixels; use `resources.getDimension(R.dimen.some_value)` para dimensões em dp
//         //   setColor(Color.parseColor("#80FFFFFF")) // Cor com opacidade
//        }
//        recyclerView.background = roundedBackground

        observeViewModel()
        newsViewModel.getTopHeadlines("us", apiKey)
    }

    private fun observeViewModel() {
        newsViewModel.articles.observe(this) { articles ->
            recyclerView.adapter = NewsAdapter(articles)
        }

        newsViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

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
//        fetchNews()
//    }


//    private fun fetchNews() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = RetrofitInstance.api.getTopHeadlines("us", apiKey)
//                val sortedArticles = response.articles.sortedByDescending { it.publishedAt }
//
//                runOnUiThread {
//                    recyclerView.adapter = NewsAdapter(sortedArticles)
//                }
//            } catch (e: Exception) {
//                runOnUiThread {
//                    Toast.makeText(this@NewsView, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

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
}