package com.app.marvel;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {
        ViewInteraction verticalViewPager = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager.perform(swipeLeft());

        ViewInteraction verticalViewPager2 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager2.perform(swipeLeft());

        ViewInteraction verticalViewPager3 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager3.perform(swipeLeft());

        ViewInteraction verticalViewPager4 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager4.perform(swipeLeft());

        ViewInteraction verticalViewPager5 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager5.perform(swipeRight());

        ViewInteraction verticalViewPager6 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager6.perform(swipeLeft());

        ViewInteraction verticalViewPager7 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager7.perform(swipeRight());

        ViewInteraction verticalViewPager8 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager8.perform(swipeRight());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.generic_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction buttonCustom = onView(
                allOf(withId(R.id.detail_main_header_screen_options), withText("More Info"),
                        withParent(withId(R.id.detail_main_header_screen_container)),
                        isDisplayed()));
        buttonCustom.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.tabs_pager),
                        withParent(withId(R.id.tabDetailContainer)),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Series"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.tabs_pager),
                        withParent(withId(R.id.tabDetailContainer)),
                        isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Events"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.tabs_pager),
                        withParent(withId(R.id.tabDetailContainer)),
                        isDisplayed()));
        viewPager3.perform(swipeRight());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Comics"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction viewPager4 = onView(
                allOf(withId(R.id.tabs_pager),
                        withParent(withId(R.id.tabDetailContainer)),
                        isDisplayed()));
        viewPager4.perform(swipeRight());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.main_collapsing)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction verticalViewPager9 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager9.perform(swipeLeft());

        ViewInteraction verticalViewPager10 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager10.perform(swipeLeft());

        ViewInteraction verticalViewPager11 = onView(
                allOf(withId(R.id.pager_cover_flow_horizontal), isDisplayed()));
        verticalViewPager11.perform(swipeRight());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.generic_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.main_collapsing)))),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

    }

}
