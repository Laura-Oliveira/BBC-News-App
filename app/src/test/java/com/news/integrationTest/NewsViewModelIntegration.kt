package com.news.integrationTest

import android.view.View
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import com.news.R
import com.news.listNews.ui.NewsView
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsViewModelIntegrationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(NewsView::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testArticlesAreDisplayedInRecyclerView() = runBlocking {
        // Aguarda até que os artigos sejam carregados e exibidos
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        // Verifica se a RecyclerView contém ao menos um item
        onView(withId(R.id.recyclerView)).check { view, _ ->
            val recyclerView = view as RecyclerView
            assert(recyclerView.adapter?.itemCount ?: 0 > 0)
        }

        // Verifica se o título do primeiro artigo é exibido corretamente
        onView(allOf(isDescendantOfA(withId(R.id.recyclerView)), withText("Title")))
            .check(matches(isDisplayed()))
    }
}