package com.example.menutestapp


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
allOf(withId(R.id.text_home), withText("This is home Fragment"),
withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
isDisplayed()))
        textView.check(matches(withText("This is home Fragment")))
        
        val textView2 = onView(
allOf(withText("Home"),
withParent(allOf(withId(R.id.toolbar),
withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))),
isDisplayed()))
        textView2.check(matches(withText("Home")))
        
        val imageButton = onView(
allOf(withId(R.id.fab),
withParent(allOf(withId(R.id.app_bar_main),
withParent(withId(R.id.drawer_layout)))),
isDisplayed()))
        imageButton.check(matches(isDisplayed()))
        
        val imageButton2 = onView(
allOf(withId(R.id.fab),
withParent(allOf(withId(R.id.app_bar_main),
withParent(withId(R.id.drawer_layout)))),
isDisplayed()))
        imageButton2.check(matches(isDisplayed()))
        
        val appCompatImageButton = onView(
allOf(withContentDescription("Open navigation drawer"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton.perform(click())
        
        val navigationMenuItemView = onView(
allOf(withId(R.id.nav_gallery),
childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.nav_view),
0)),
2),
isDisplayed()))
        navigationMenuItemView.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
