package com.ntk.quizzy.Views.Activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.ntk.quizzy.R;

import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class QuestionActivityTest {

    @Test
    public void TestQuestionTitleIsAppears() {
        ActivityScenario QuestionActivity = ActivityScenario.launch(QuestionActivity.class); // Lunch QuestionActivity
        onView(withId(R.id.questitle)).check(matches(isDisplayed())); //Look at the Ques Title
    }

    @Test
    public void TestIfTheScoreIncreaseOneOnRightAnswer() {
        ActivityScenario QuestionActivity = ActivityScenario.launch(QuestionActivity.class); // Launch QuestionActivity
        try {
            onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
            onView(withId(R.id.questitle)).check(matches(withText("Ques 1")));
            onView(withId(R.id.btnop3)).perform(click());
            onView(withId(R.id.scoreTextView)).check(matches(withText("1")));
        } catch (AssertionFailedError e) {
            try {
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
                onView(withId(R.id.questitle)).check(matches(withText("Ques 2")));
                onView(withId(R.id.btnop2)).perform(click());
                onView(withId(R.id.scoreTextView)).check(matches(withText("1")));
            } catch (AssertionFailedError ee) {
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
                onView(withId(R.id.questitle)).check(matches(withText("Ques 3")));
                onView(withId(R.id.btnop4)).perform(click());
                onView(withId(R.id.scoreTextView)).check(matches(withText("1")));
            }
        }

    }

    @Test
    public void TestIfTheScoreStayZeroOnWrongAnswer() {
        ActivityScenario QuestionActivity = ActivityScenario.launch(QuestionActivity.class); // Lunch QuestionActivity
        try {
            onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
            onView(withId(R.id.questitle)).check(matches(withText("Ques 1")));
            onView(withId(R.id.btnop1)).perform(click());
            onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
        } catch (AssertionFailedError e) {
            try {
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
                onView(withId(R.id.questitle)).check(matches(withText("Ques 2")));
                onView(withId(R.id.btnop1)).perform(click());
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
            } catch (AssertionFailedError ee) {
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
                onView(withId(R.id.questitle)).check(matches(withText("Ques 3")));
                onView(withId(R.id.btnop1)).perform(click());
                onView(withId(R.id.scoreTextView)).check(matches(withText("0")));
            }
        }
    }
}