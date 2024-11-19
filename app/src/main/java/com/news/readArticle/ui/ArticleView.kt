package com.news.readArticle.ui

import ArticleViewModel
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.news.R
import com.news.readArticle.data.Article
import com.squareup.picasso.Picasso

class ArticleView : AppCompatActivity()
{
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article)

        //Recovery the article object sent by the NewsView
        val article = intent.getParcelableExtraCompat<Article>("article_key")

        Log.w("Article [ArticleView]", "Article received from Home: $article")

        article?.let {
            // Sends the article to the ViewModel
            articleViewModel.setArticle(it)
            Log.w("Article [ArticleView]", "Article passed to ViewModel: $it")
        }

        // Observe changes on the article
        articleViewModel.article.observe(this) { article ->
            article?.let {
                //If the article is removed, doesn't shows anything
                if (article.title == "Removed" && article.description == "Removed") {
                    findViewById<TextView>(R.id.articleTitle).visibility = View.GONE
                    findViewById<TextView>(R.id.articleDescription).visibility = View.GONE
                    findViewById<ImageView>(R.id.articleImage).visibility = View.GONE
                }
                else
                {
                    //If the article exits, shows title, image and description
                    val imageView: ImageView = findViewById(R.id.articleImage)
                    val titleTextView: TextView = findViewById(R.id.articleTitle)
                    val descriptionTextView: TextView = findViewById(R.id.articleDescription)
                    val contentTextView: TextView = findViewById(R.id.articleContent)

                    //Shows headline data, if any data is available
                    titleTextView.text = it.title ?: "No title available"
                    descriptionTextView.text = it.description ?: "No content available"
                    contentTextView.text = it.content ?: "No content available"
                    val imageUrl = it.urlToImage
                    Log.d("Article [ArticleView]", "Image UR Received: ${it.urlToImage}")

                    // Load the Image from URL
                    imageUrl?.let { url ->
                        //If the URL is valid and exists a image, shows it
                        //Cache image with Picasso
                        if(!article.urlToImage.isNullOrEmpty())
                        { Picasso.get().load(url).into(imageView) }

                        //If article is null or empty, doesn't show the image
                        else { findViewById<ImageView>(R.id.articleImage).visibility = View.GONE }
                    } ?: run {
                        //If the url is invalid, and the article was removed, doesn't show the image
                        if(article.title == "Removed" || article.description == "Removed")
                        { findViewById<ImageView>(R.id.articleImage).visibility = View.GONE }

                        // If the activity doesn't receive a valid URL, load a default image
                        imageView.setImageResource(R.drawable.bbc)
                    }
                }
            } ?: run {
                //If the article is null, shows a message error
                findViewById<TextView>(R.id.articleContent).text = "No article content available"
                findViewById<TextView>(R.id.article).visibility = View.GONE
                findViewById<ConstraintLayout>(R.id.itemHeadline).visibility = View.GONE
                findViewById<TextView>(R.id.articleTitle).visibility = View.GONE
                findViewById<TextView>(R.id.articleDescription).visibility = View.GONE
                findViewById<ImageView>(R.id.articleImage).visibility = View.GONE
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