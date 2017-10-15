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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlphaJumpingSectionsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void alphaJumpingSectionsTest() {

        pause(5000);

        // Click on Search Bar at top of app
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.search_container), isDisplayed()));
        linearLayout.perform(click());

        // Enter search criteria and send it to app
        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Charlie Cox"), closeSoftKeyboard());

        // Click first hit from returned list (index 0)
        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.page_list_item_container),
                        childAtPosition(
                                allOf(withId(R.id.search_results_list),
                                        withParent(withId(R.id.search_results_container))),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());

        pause(5000);

        // click on Table of Contents on Action Bar at bottom of app
        clickOnTableOfContents();

        // click on 1st section of Table of Contents
        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        1),
                        isDisplayed()));
        linearLayout3.perform(click());

        pause(5000);

        clickOnTableOfContents();

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        2),
                        isDisplayed()));
        linearLayout4.perform(click());

        pause(5000);

/*
After clicking the Table of Contents button the second time at the bottom,
the whole bottom tool bar disappears, causing every attempt to click every section after
that to fail (since it's no longer visible).  However, we could make it visible only if
we manually scrolled up on the page
*/
/*        clickOnTableOfContents();

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        3),
                        isDisplayed()));
        linearLayout5.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout6 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        4),
                        isDisplayed()));
        linearLayout6.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout7 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        5),
                        isDisplayed()));
        linearLayout7.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout8 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        6),
                        isDisplayed()));
        linearLayout8.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout9 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        7),
                        isDisplayed()));
        linearLayout9.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout10 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        8),
                        isDisplayed()));
        linearLayout10.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout11 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        9),
                        isDisplayed()));
        linearLayout11.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout12 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        10),
                        isDisplayed()));
        linearLayout12.perform(click());

        clickOnTableOfContents();

        ViewInteraction linearLayout13 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        11),
                        isDisplayed()));
        linearLayout13.perform(click());*/
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

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the View containing the table of contents is not visible, attempting to click it will crash the test
     * Here, if it's not visible, we render it, then attempt to click it a second time
     */
    private void clickOnTableOfContents() {

        ViewInteraction appCompatTextView = onView(allOf(withText("b#5"), isDisplayed()));

        appCompatTextView.perform(click());
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
