//package com.news.readArticle.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Parcelable
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.news.R
//import com.news.data.Article
//import com.squareup.picasso.Picasso
//
//class ArticleView : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.article)
//
//        // Recuperar o artigo passado pela Intent usando a função de extensão
//        val article = intent.getParcelableExtraCompat<Article>("article_key")
//
//        // Verificar se o artigo existe
//        article?.let {
//            // Exibir título, conteúdo e imagem
//            val titleTextView: TextView = findViewById(R.id.articleTitle)
//            val contentTextView: TextView = findViewById(R.id.articleContent)
//            val imageView: ImageView = findViewById(R.id.articleImage)
//
//            titleTextView.text = it.title
//            contentTextView.text = it.content
//
//            // Carregar imagem usando Picasso
//            it.urlToImage?.let { imageUrl ->
//                Picasso.get().load(imageUrl).into(imageView)
//            }
//        }
//    }
//}
//
//// Função de Extensão para Intent, para recuperar o Parcelable de forma segura
//inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
//    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
//        // Para Android 33 (API nível 33) e versões posteriores
//        this.getParcelableExtra(name, T::class.java)
//    } else {
//        // Para versões anteriores, usa o método antigo
//        @Suppress("DEPRECATION")
//        this.getParcelableExtra(name)
//    }
//}
//
//package com.news.readArticle.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Parcelable
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.news.R
//import com.news.data.Article
//import com.squareup.picasso.Picasso
//
//class ArticleView : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.article)
//
//        // Recuperar o artigo passado pela Intent usando a função de extensão
//        val article = intent.getParcelableExtraCompat<Article>("article_key")
//
//        // Verificar se o artigo existe
//        article?.let {
//            // Exibir título, conteúdo e imagem
//            val imageView: ImageView = findViewById(R.id.articleImage)
//            val titleTextView: TextView = findViewById(R.id.articleTitle)
//            val contentTextView: TextView = findViewById(R.id.articleContent)
//
//            // Exibir título e conteúdo
//            titleTextView.text = it.title ?: "No title available"
//            contentTextView.text = it.content ?: "No content available"
//
//            // Carregar imagem usando Picasso, se a URL da imagem for válida
//            it.urlToImage?.let { imageUrl ->
//                Picasso.get().load(imageUrl).into(imageView)
//            } ?: run {
//                imageView.setImageResource(R.drawable.bbc) // Caso não tenha imagem, use uma imagem de placeholder
//            }
//        } ?: run {
//            // Caso o artigo seja nulo, exiba um erro ou mensagem
//            findViewById<TextView>(R.id.articleTitle).text = "No article data available"
//            findViewById<TextView>(R.id.articleContent).text = "No article content available"
//        }
//    }
//}
//
//// Função de Extensão para Intent, para recuperar o Parcelable de forma segura
//inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
//    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
//        // Para Android 33 (API nível 33) e versões posteriores
//        this.getParcelableExtra(name, T::class.java)
//    } else {
//        // Para versões anteriores, usa o método antigo
//        @Suppress("DEPRECATION")
//        this.getParcelableExtra(name)
//    }
//}


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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.news.R
import com.news.data.Article
import com.squareup.picasso.Picasso

class ArticleView : AppCompatActivity() {

    // Usando o ViewModelProvider para obter o ArticleViewModel
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
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
              //  val imageUrl = it.urlToImage
                Log.d("Article [ArticleView]", "Image UR Received: ${it.urlToImage}")

//                Picasso.get()
//                    .load("https://static.politico.com/1c/cb/c1b49cf4459bb4457ebad2f83f74/201002-cliff-sims-ap-773.jpg")
//                    .into(imageView)  // A 'imageView' é onde você quer exibir a imagem

                // Carregar imagem com Picasso
                it.urlToImage?.let { url ->
                    Picasso.get().load(url).into(imageView)
                } ?: run {
                    imageView.setImageResource(R.drawable.bbc) // Caso não tenha URL, usa uma imagem de fallback
                }



//                // Carregar imagem usando Picasso, se a URL da imagem for válida
//                it.urlToImage?.let { imageUrl ->
//                    Picasso.get().load(imageUrl).into(imageView)
//                } ?: run {
//                    imageView.setImageResource(R.drawable.bbc) // Caso não tenha imagem, use uma imagem de placeholder
//                }
            } ?: run {
                // Caso o artigo seja nulo, exiba um erro ou mensagem
                findViewById<TextView>(R.id.articleTitle).text = "No article data available"
                findViewById<TextView>(R.id.articleContent).text = "No article content available"
            }
        }
    }

    //// Função de Extensão para Intent, para recuperar o Parcelable de forma segura
inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            // Para Android 33 (API nível 33) e versões posteriores
            this.getParcelableExtra(name, T::class.java)
        } else {
            // Para versões anteriores, usa o método antigo
            @Suppress("DEPRECATION")
            this.getParcelableExtra(name)
        }
    }
}