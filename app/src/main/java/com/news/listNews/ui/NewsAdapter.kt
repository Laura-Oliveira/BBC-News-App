package com.news.listNews.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.listNews.domain.Article
import com.squareup.picasso.Picasso

class NewsAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        val newsDescription: TextView = view.findViewById(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]

        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description ?: "No description available"

        if (!article.urlToImage.isNullOrEmpty()) {
            Picasso.get().load(article.urlToImage).into(holder.newsImage)
        }
    }

    override fun getItemCount() = articles.size
}