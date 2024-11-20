package com.news.readArticle.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.news.R
import com.news.databinding.ArticleBinding
import com.news.readArticle.data.Article
import com.news.readArticle.domain.ArticleViewModel
import com.squareup.picasso.Picasso

class ArticleView : AppCompatActivity()
{
    private lateinit var bindingArticle: ArticleBinding
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Data Binding configuration
        bindingArticle = ArticleBinding.inflate(layoutInflater)
        setContentView(bindingArticle.root)

        //Associate viewModel to layout
        bindingArticle.articleViewModel = articleViewModel
        bindingArticle.lifecycleOwner = this

        val drawable = ContextCompat.getDrawable(this, R.drawable.list_background)
        drawable?.alpha = 150
        bindingArticle.article.background = drawable

        //Retrieve article info sent from homepage
        val article = intent.getParcelableExtraCompat<Article>("article_key")
        article?.let {
            articleViewModel.setArticle(it) //Sent it to viewModel
        }

        // Observe changes on the article
        articleViewModel.article.observe(this) { article ->
            article?.let {
                //If the article is removed, doesn't shows anything
                if (article.title == "Removed" && article.description == "Removed") {
                    bindingArticle.articleTitle.visibility = View.GONE
                    bindingArticle.articleDescription.visibility = View.GONE
                    bindingArticle.articleImage.visibility = View.GONE
                }
                else
                {
                    //Shows headline data, if any data is available
                    bindingArticle.articleTitle.text = it.title ?: "No title available"
                    bindingArticle.articleDescription.text = it.description ?: "No content available"
                    bindingArticle.articleContent.text = it.content ?: "No content available"
                    Log.d("Article [ArticleView]", "Image UR Received: ${it.urlToImage}")

                    it.urlToImage?.let { url ->
                        //If the URL is valid and exists a image, shows it
                        //Load and cache image with Picasso
                        if (url.isNotEmpty())
                        { Picasso.get().load(url).into(bindingArticle.articleImage) }
                        //If article is null or empty, doesn't show the image
                        else
                        { bindingArticle.articleImage.setImageResource(R.drawable.bbc) }
                    } ?: run { bindingArticle.articleImage.visibility = View.GONE
                    //    bindingArticle.articleImage.setImageResource(R.drawable.bbc)
                    }
                }
            } ?: run {
                // Caso o artigo seja nulo, esconder elementos
                bindingArticle.article.visibility = View.GONE
                bindingArticle.articleTitle.visibility = View.GONE
                bindingArticle.articleDescription.visibility = View.GONE
                bindingArticle.articleImage.visibility = View.GONE
                bindingArticle.articleContent.visibility = View.GONE
            }
        }
    }

    //Retrieve the Intent extension in case of API LEVEL 33+
    private inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            this.getParcelableExtra(name, T::class.java)
        }
        else
        {   // If a API LEVEL < 33, use the deprecated function
            @Suppress("DEPRECATION")
            this.getParcelableExtra(name)
        }
    }
}