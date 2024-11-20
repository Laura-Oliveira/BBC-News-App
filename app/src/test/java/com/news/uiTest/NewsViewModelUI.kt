package com.news.uiTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.news.R
import com.news.listNews.ui.NewsView
import org.junit.Test

class NewsViewModelUI
{
    @Test
    fun testGetTopHeadlinesSuccess()
    {
        //Initialize the NewsView
        ActivityScenario.launch(NewsView::class.java)

        //Check if the RecyclerView is visible
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        //Check if the screen has at least one headline visible
        onView(withText("Title"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testGetTopHeadlinesError()
    {
        //Initialize the NewsView
        ActivityScenario.launch(NewsView::class.java)

        //Check if the message erro is showing
        onView(withText("Network Error"))
            .check(matches(isDisplayed()))
    }
}