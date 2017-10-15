package org.wikipedia.test;


import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.NoMatchingViewException;
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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlphaManageReadingListTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void alphaManageReadingListTest() {

        pause(5000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.search_container), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Charlie Cox"), closeSoftKeyboard());

        pause(5000);

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.page_list_item_container),
                        childAtPosition(
                                allOf(withId(R.id.search_results_list),
                                        withParent(withId(R.id.search_results_container))),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());

        pause(5000);

        ViewInteraction appCompatTextView = onView(
                allOf(withText("b#1"), isDisplayed()));
        appCompatTextView.perform(click());

        // if this is the first time the app is run, the onboarding button will appear
        // handle both cases (whether it's the first time or not)
        try {
            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.onboarding_button), withText("Got it"),
                            withParent(withId(R.id.onboarding_container)),
                            isDisplayed()));
            appCompatButton.perform(click());
        } catch (NoMatchingViewException e) {
            // Click Create New if the Got It didn't appear
            ViewInteraction createNewListButton = onView(allOf(withId(R.id.create_button),
                    isDisplayed()));
            createNewListButton.perform(click());
        }

        pause(5000);

        ViewInteraction plainPasteEditText = onView(
                allOf(withId(R.id.reading_list_title)));
        plainPasteEditText.perform(scrollTo(), replaceText("Daredevil Actors"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        pause(5000);

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_page_search), withContentDescription("Search Wikipedia"), isDisplayed()));
        actionMenuItemView.perform(click());

        pause(5000);

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("Ben Affleck"), closeSoftKeyboard());

        pause(5000);

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.page_list_item_container),
                        childAtPosition(
                                allOf(withId(R.id.search_results_list),
                                        withParent(withId(R.id.search_results_container))),
                                0),
                        isDisplayed()));
        linearLayout3.perform(click());

        pause(5000);

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("b#1"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout4.perform(click());

        pause(5000);

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.page_toolbar)),
                        isDisplayed()));
        imageButton.perform(click());

        pause(5000);

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("My lists"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_container),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Daredevil Actors")));

        pause(5000);

        ViewInteraction linearLayout5 = onView(
                allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout5.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.button_edit), withContentDescription("Edit reading list details"), isDisplayed()));
        floatingActionButton.perform(click());

        pause(5000);

        ViewInteraction plainPasteEditText2 = onView(
                allOf(withId(R.id.reading_list_title), withText("Daredevil Actors")));
        plainPasteEditText2.perform(scrollTo(), click());

        ViewInteraction plainPasteEditText3 = onView(
                allOf(withId(R.id.reading_list_title)));
        plainPasteEditText3.perform(scrollTo(), replaceText("Daredevil Stars"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        pause(5000);

        // if this is the first time the app is run, the snackbar_action button will appear
        // handle both cases (whether it's the first time or not)
        try {
            ViewInteraction appCompatButton4 = onView(
                    allOf(withId(R.id.snackbar_action), withText("Got it"), isDisplayed()));
            appCompatButton4.perform(click());
        } catch (NoMatchingViewException e) {
            // no action necessary, just move on
        }

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.button_edit), withContentDescription("Edit reading list details"), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.reading_list_delete_link), withText("Delete reading list")));
        appCompatTextView4.perform(scrollTo(), click());

        pause(5000);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

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
