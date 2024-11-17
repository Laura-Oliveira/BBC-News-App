package com.news.uiTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.news.R
import com.news.listNews.ui.NewsView
import org.junit.Test

class NewsViewModelUI {

    @Test
    fun testGetTopHeadlinesSuccess() {
        // Lança a atividade principal da tela de notícias
        ActivityScenario.launch(NewsView::class.java)

        // Verifica se o RecyclerView está visível com os dados carregados
        onView(withId(R.id.recyclerView)) // Substituir pelo ID real
            .check(matches(isDisplayed()))

        // Verifica se um item específico está na lista (exemplo: título do artigo)
        onView(withText("Title")) // Substituir pelo título esperado
            .check(matches(isDisplayed()))
    }

    @Test
    fun testGetTopHeadlinesError() {
        // Lança a atividade principal da tela de notícias
        ActivityScenario.launch(NewsView::class.java)

        // Verifica se a mensagem de erro é exibida
        onView(withText("Network Error")) // Substituir pelo texto de erro esperado
            .check(matches(isDisplayed()))
    }
}