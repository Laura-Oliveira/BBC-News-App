package com.news.listNews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.readArticle.data.Article
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
        holder.newsTitle.text = article.title ?: ""
        holder.newsDescription.text = article.description ?: ""

        // Cheks if the article was removed, if yes, clean all the fields on the item list
        if (article.title == "Removed" && article.description == "Removed")
        {
            holder.newsTitle.text = ""
            holder.newsDescription.text = ""
            holder.newsImage.visibility = View.GONE
        }

        //Load the image, if the URL is valid
        if (!article.urlToImage.isNullOrEmpty())
        {
            Picasso.get()
                .load(article.urlToImage)
                .into(holder.newsImage)
        }
        //If the URL isn't valid, shows a default image
        else
        { holder.newsImage.setImageResource(R.mipmap.ic_bbc) }

       //  When the user tap a headline item, sends the article object to the next screen
        holder.itemView.setOnClickListener { onItemClick(article) }
    }
    override fun getItemCount() = articles.size
}