package com.news.uiTest

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.news.readArticle.data.Article
import com.news.listNews.ui.NewsView
import com.news.readArticle.ui.ArticleView
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class NavigationUI
{
    @get:Rule
    var activityRule = ActivityTestRule(NewsView::class.java)

    @Test
    fun testNavigateToArticleView()
    {
        // Launching NewsView activity
        val scenario = ActivityScenario.launch(NewsView::class.java)

        // Initialize the article object
        val article = Article("Title", "Content", "Description", "url", "2024-01-01")

        // Preparing the intent with the article data
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ArticleView::class.java)
        intent.putExtra("article_key", article)

        // Check that the intent leads to the correct ArticleView activity
        scenario.onActivity { activity ->
            // Start the ArticleView activity with the intent
            activity.startActivity(intent)

            // Verify if the started activity is ArticleView
            assertTrue(activity is NewsView)

            // Verify the intent contains the correct article data
            val receivedArticle = intent.getParcelableExtra<Article>("article_key")
            assert(receivedArticle?.title == article.title)
            assert(receivedArticle?.content == article.content)
        }
    }
}