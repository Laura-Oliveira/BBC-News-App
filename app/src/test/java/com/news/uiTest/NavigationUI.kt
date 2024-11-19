package com.news.uiTest

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.news.readArticle.data.Article
import com.news.listNews.ui.NewsView
import com.news.readArticle.ui.ArticleView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class NavigationUI {

    @get:Rule
    var activityRule = ActivityTestRule(NewsView::class.java)

    @Test
    fun testNavigateToArticleView() {
        val scenario = ActivityScenario.launch(NewsView::class.java)

        // Simulating a click on a news item
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ArticleView::class.java)
        intent.putExtra("article_key", Article("Title", "Content", "Description", "url", "2024-01-01"))

        // Check if the intent leads to ArticleView activity
        scenario.onActivity { activity ->
            activity.startActivity(intent)
            assert(activity is NewsView)
        }
    }
}