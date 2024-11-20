package com.news.uiTest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.news.R
import com.news.listNews.ui.NewsView
import org.junit.Assert.assertNotNull
import org.junit.Test

class NewsViewUI
{
    @Test
    fun testRecyclerViewIsNotNull()
    {
        //Initialize the Activity
        ActivityScenario.launch(NewsView::class.java).use { scenario ->
            scenario.onActivity { activity ->
                //Retrieve the RecyclerView directly from the Activity
                val recyclerView: RecyclerView = activity.findViewById(R.id.recyclerView)

                //Check if the RecyclerView is not null
                assertNotNull(recyclerView)

                //Check if the RecyclerView is visible
                assert(recyclerView.visibility == View.VISIBLE)
            }
        }
    }

    @Test
    fun testRecyclerViewIsPopulated()
    {
        //Initialize the Activity
        ActivityScenario.launch(NewsView::class.java)

        //Check if the RecyclerView is visible
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        //Check if at least one item on RecyclerView is visible
        onView(withId(R.id.recyclerView))
            .check { view, _ ->
                val recyclerView = view as RecyclerView
                assert((recyclerView.adapter?.itemCount ?: 0) > 0)
            }
    }

    @Test
    fun testNewsItemIsBoundCorrectly()
    {
        //Initialize the Activity
        ActivityScenario.launch(NewsView::class.java)

        //Check if the title is visible
        onView(withText("Title"))
            .check(matches(isDisplayed()))
    }
}