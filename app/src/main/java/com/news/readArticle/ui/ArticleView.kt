package com.news.readArticle.ui

import ArticleViewModel
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.news.R
import com.news.data.Article
import com.squareup.picasso.Picasso

class ArticleView : AppCompatActivity()
{
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article)

        // Recuperar o artigo passado pela Intent e configurar o ViewModel
        val article = intent.getParcelableExtraCompat<Article>("article_key")

        Log.w("Article [ArticleView]", "Article received from Home: $article")

        article?.let {
            // Passar o artigo para o ViewModel
            articleViewModel.setArticle(it)
            Log.w("Article [ArticleView]", "Article passed to ViewModel: $it")
        }

        // Observar mudanças no artigo
        articleViewModel.article.observe(this) { article ->
            article?.let {
                Log.w("Article [ArticleView]", "Displaying article: Title = ${it.title}, Content = ${it.content}, URL = ${it.urlToImage}")

                // Exibir título, conteúdo e imagem
                val imageView: ImageView = findViewById(R.id.articleImage)
                val titleTextView: TextView = findViewById(R.id.articleTitle)
                val descriptionTextView:TextView = findViewById(R.id.articleDescription)
                val contentTextView: TextView = findViewById(R.id.articleContent)

                // Exibir título e conteúdo
                titleTextView.text = it.title ?: "No title available"
                descriptionTextView.text = it.description ?: "No content available"
                contentTextView.text = it.content ?: "No content available"
                val imageUrl = it.urlToImage
                Log.d("Article [ArticleView]", "Image UR Received: ${it.urlToImage}")

                // Load the Imagem from URL
                imageUrl?.let { url ->
                    Picasso.get().load(url).into(imageView)
                } ?: run{
                    //If the activity doesn't receive a valid URL, load a default image
                    imageView.setImageResource(R.drawable.bbc)
                }

            } ?: run {
                // If the Article is null, shows an error or a message
                // findViewById<TextView>(R.id.articleTitle).text = "No article data available"
                findViewById<TextView>(R.id.articleContent).text = "No article content available"
            }
        }
    }

    //// Retrieve the Intent extension in case of API LEVEL 33+
inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T?
{
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
        { this.getParcelableExtra(name, T::class.java) }
        else
        {
            // If a API LEVEL < 33, use the deprecated function
            @Suppress("DEPRECATION")
            this.getParcelableExtra(name)
        }
    }
}