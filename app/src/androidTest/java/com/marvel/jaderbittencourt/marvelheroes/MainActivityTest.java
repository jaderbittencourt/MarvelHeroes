package com.marvel.jaderbittencourt.marvelheroes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marvel.jaderbittencourt.marvelheroes.ui.ActivityMain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<ActivityMain> mActivityRule = new ActivityTestRule<>(
            ActivityMain.class);

    @Test
    public void shouldHideRetryContainerAndShow() {
        // this method should be tested offline

        // click retry button
        onView(withId(R.id.something_wrong_retry)).perform(click());
        onView(withId(R.id.something_wrong_container)).check(matches(isDisplayed()));
    }
}
