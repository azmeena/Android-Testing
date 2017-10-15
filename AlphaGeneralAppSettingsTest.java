package org.wikipedia.test;


import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlphaGeneralAppSettingsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void alphaGeneralAppSettingsTest() {
        ViewInteraction textView = onView(
                allOf(withId(org.wikipedia.R.id.menu_overflow_button), withContentDescription("More options"),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(org.wikipedia.R.id.menu_overflow_button), withContentDescription("More options"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(org.wikipedia.R.id.explore_overflow_settings), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Settings")));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(org.wikipedia.R.id.explore_overflow_settings), withText("Settings"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(org.wikipedia.R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(org.wikipedia.R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(org.wikipedia.R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(org.wikipedia.R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()));
        recyclerView4.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(org.wikipedia.R.id.action_bar),
                                withParent(withId(org.wikipedia.R.id.action_bar_container)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(org.wikipedia.R.id.action_bar),
                                withParent(withId(org.wikipedia.R.id.action_bar_container)))),
                        isDisplayed()));
        imageButton2.perform(click());
    }

    @After
    public void cleanUp() {
        while (true) {
            try {
                pressBack();
            } catch (NoActivityResumedException e) {
                return;
            }
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
