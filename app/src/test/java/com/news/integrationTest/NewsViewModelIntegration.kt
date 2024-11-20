package com.news.integrationTest

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
class NewsViewModelIntegrationTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(NewsView::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testArticlesAreDisplayedInRecyclerView() = runBlocking{
        //Checks if the recyclerView is visible
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        //Checks if RecyclerView have itens
        onView(withId(R.id.recyclerView)).check { view, _ ->
            val recyclerView = view as RecyclerView
            // A asserção garante que o RecyclerView tem itens
            assert((recyclerView.adapter?.itemCount ?: 0) > 0)
        }

        //Checks if the article title is showing on the recyclerView
        onView(allOf(isDescendantOfA(withId(R.id.recyclerView)), withText("Title")))
            .check(matches(isDisplayed()))

        //Checks if the article description is is showing on the recyclerView
        onView(withId(R.id.articleDescription))
            .check(matches(isDisplayed()))
    }
}