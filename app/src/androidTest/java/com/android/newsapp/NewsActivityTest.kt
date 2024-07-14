package com.android.newsapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.android.newsapp.presentation.view.NewsActivity

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsActivityTest {
    @Test
    fun testBottomNavigation_isDisplayed() {
        // Launch NewsActivity
        val activityScenario = ActivityScenario.launch(NewsActivity::class.java)

        // Check if BottomNavigationView is displayed
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }

    @Test
    fun testBottomNavigation_hasCorrectMenuItems() {
        // Launch NewsActivity
        val activityScenario = ActivityScenario.launch(NewsActivity::class.java)

        // Check if BottomNavigationView has correct menu items
        onView(withId(R.id.bottomNavigationView)).check(matches(hasDescendant(withText("Headlines"))))
        onView(withId(R.id.bottomNavigationView)).check(matches(hasDescendant(withText("Favorites"))))
        onView(withId(R.id.bottomNavigationView)).check(matches(hasDescendant(withText("Search"))))
    }
}