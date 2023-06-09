package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){
        /* Test automation using Espresso to enter in '50.00' into the EditTextView
        This also closes the virtual keyboard just in case the device being used for
        is small
        * */
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00")).perform(
            ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.00"))))
        onView(withId(R.id.total_cost)).check(matches(withText(containsString("$50.00"))))
        onView(withId(R.id.total_amount)).check(matches(withText(containsString("$60.00"))))

    }

}