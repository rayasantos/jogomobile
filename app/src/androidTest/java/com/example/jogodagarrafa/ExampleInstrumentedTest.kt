package com.example.jogodagarrafa
import androidx.test.espresso.assertion.ViewAssertions

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class MainActivityTest {

    @get:Rule
    public val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    public fun testStartGameButton() {
        // Verificar se o botão "Start Game" está visível
        Espresso.onView(ViewMatchers.withId(R.id.btn_start_game)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Clicar no botão "Start Game"
        Espresso.onView(ViewMatchers.withId(R.id.btn_start_game)).perform(ViewActions.click())

        // Verificar se a atividade do jogo foi iniciada
        val gameActivityScenario = ActivityScenario.launch(GameActivity::class.java)
        gameActivityScenario.onActivity { activity ->
           
        }
    }
}

