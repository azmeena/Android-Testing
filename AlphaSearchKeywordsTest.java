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
import org.wikipedia.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlphaSearchKeywordsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void alphaSearchKeywordsTest() {

        pause(5000);

        ViewInteraction textView = onView(
                allOf(withText("Search Wikipedia"),
                        childAtPosition(
                                allOf(withId(R.id.search_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Search Wikipedia")));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.search_container), isDisplayed()));
        linearLayout.perform(click());

        pause(5000);

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Charlie Cox"), closeSoftKeyboard());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.page_list_item_container),
                        childAtPosition(
                                allOf(withId(R.id.search_results_list),
                                        withParent(withId(R.id.search_results_container))),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());

        pause(5000);

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.view_article_header_text), withText("Charlie Cox\nEnglish actor"),
                        childAtPosition(
                                allOf(withId(R.id.view_article_header_container),
                                        childAtPosition(
                                                withId(R.id.page_header_view),
                                                0)),
                                1),
                        isDisplayed()));
        //textView2.check(matches(withText("Charlie Cox\nEnglish actor")));

        ViewInteraction actionBar$Tab = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.page_actions_tab_layout),
                                0),
                        3),
                        isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView = onView(
                allOf(withText("b#4"), isDisplayed()));
        appCompatTextView.perform(click());

        pause(5000);

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("Daredevilo"), closeSoftKeyboard());

        pause(5000);

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.find_in_page_match), withText("0/0"),
                        childAtPosition(
                                allOf(withId(R.id.find_in_page_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.support.v7.widget.LinearLayoutCompat.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("0/0")));

        pause(5000);

        ViewInteraction searchAutoComplete3 = onView(
                allOf(withId(R.id.search_src_text),  //withText("Daredevilo"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete3.perform(replaceText("Daredevil"), closeSoftKeyboard());

        pause(5000);

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.find_in_page_match), withText("1/4"),
                        childAtPosition(
                                allOf(withId(R.id.find_in_page_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.support.v7.widget.LinearLayoutCompat.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("1/4")));

        pause(5000);

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.find_in_page_next), withContentDescription("Find next"),
                        withParent(withId(R.id.find_in_page_container)),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.find_in_page_next), withContentDescription("Find next"),
                        withParent(withId(R.id.find_in_page_container)),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.find_in_page_next), withContentDescription("Find next"),
                        withParent(withId(R.id.find_in_page_container)),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.find_in_page_next), withContentDescription("Find next"),
                        withParent(withId(R.id.find_in_page_container)),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        pause(5000);

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.find_in_page_match), withText("1/4"),
                        childAtPosition(
                                allOf(withId(R.id.find_in_page_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.support.v7.widget.LinearLayoutCompat.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("1/4")));

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

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
