package com.news.uiTest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.news.R
import com.news.listNews.ui.NewsView
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsViewUI {

    @get:Rule
    var activityRule = ActivityTestRule(NewsView::class.java)

    @Test
    fun testRecyclerViewIsNotNull() {
        val activity = activityRule.activity
        val recyclerView: RecyclerView = activity.findViewById(R.id.recyclerView)
        assertNotNull(recyclerView)

        // Verify if the recyclerView is visible
        assert(recyclerView.visibility == View.VISIBLE)
    }
}