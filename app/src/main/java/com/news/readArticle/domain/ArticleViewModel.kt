import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.data.Article

class ArticleViewModel : ViewModel() {
    private val _article = MutableLiveData<Article?>()
    val article: LiveData<Article?> get() = _article

    // Método para configurar o artigo
    fun setArticle(article: Article) {
        _article.value = article
    }
}