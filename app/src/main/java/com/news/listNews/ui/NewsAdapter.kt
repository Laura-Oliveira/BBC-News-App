package com.news.listNews.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.data.Article
import com.news.readArticle.ui.ArticleView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
//
//class NewsAdapter(private val context: Context, private val articles: List<Article>) :
//    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
//
//    // ViewHolder padrão
//    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val newsImage: ImageView = view.findViewById(R.id.newsImage)
//        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
//        val newsDescription: TextView = view.findViewById(R.id.newsDescription)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        return NewsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val article = articles[position]
//
//        holder.newsTitle.text = article.title
//        holder.newsDescription.text = article.description ?: "No description available"
//
//        // Carregar imagem se disponível
//        if (!article.urlToImage.isNullOrEmpty()) {
//            Picasso.get().load(article.urlToImage).into(holder.newsImage)
//        }
//
//        // Configurar o clique do item
//        holder.itemView.setOnClickListener {
//            // Cria um Intent para navegar para a tela de detalhes
//            val intent = Intent(context, ArticleView::class.java)
//            intent.putExtra("article_key", article) // Passa o artigo completo
//            context.startActivity(intent) // Inicia a Activity de detalhes
//        }
//    }
//
//    override fun getItemCount() = articles.size
//}

class NewsAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit // Lambda para o clique
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

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

        // Carregar imagem com cache
        if (!article.urlToImage.isNullOrEmpty()) {
            // Usa o cache de memória e disco com políticas adequadas
            Picasso.get()
                .load(article.urlToImage)
                .memoryPolicy(MemoryPolicy.NO_CACHE) // Impede o cache de memória (opcional, dependendo do seu caso)
                .networkPolicy(NetworkPolicy.NO_STORE) // Impede o armazenamento no disco (opcional)
                .into(holder.newsImage)
        } else {
            // Usa a imagem padrão do drawable se a URL estiver vazia ou nula
            holder.newsImage.setImageResource(R.mipmap.ic_bbc)
        }

        // Definindo o clique do item
//        holder.itemView.setOnClickListener {
//            // Passando os dados para a próxima Activity
//            val context = holder.itemView.context
//            val intent = Intent(context, ArticleView::class.java).apply {
//                putExtra("article_title", article.title)
//                putExtra("article_content", article.content)
//                putExtra("article_image", article.urlToImage)
//            }
//        }
        // Definindo o clique do item
        holder.itemView.setOnClickListener {
            onItemClick(article) // Chama a função de clique passando o artigo
        }
    }

    override fun getItemCount() = articles.size
}