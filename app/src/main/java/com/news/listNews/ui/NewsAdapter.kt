package com.news.listNews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.data.Article
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        val newsDescription: TextView = view.findViewById(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int)
    {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description ?: "No description available"

        // Load the image with cache
        if (!article.urlToImage.isNullOrEmpty())
        {
            Picasso.get()
                .load(article.urlToImage)
                .memoryPolicy(MemoryPolicy.NO_CACHE) // Impede o cache de memória (opcional, dependendo do seu caso)
                .networkPolicy(NetworkPolicy.NO_STORE) // Impede o armazenamento no disco (opcional)
                .into(holder.newsImage)
        }
        else
        {
            //Set a default image if the URL is broken or empty
            holder.newsImage.setImageResource(R.mipmap.ic_bbc)
        }


        // When the user tap a headline item, sends the article object to the next screen
        holder.itemView.setOnClickListener { onItemClick(article) }
    }

    override fun getItemCount() = articles.size
}