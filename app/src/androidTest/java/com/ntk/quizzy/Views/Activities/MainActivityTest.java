package com.ntk.quizzy.Views.Activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.ntk.quizzy.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {
    @Test
    public void testTitleAndBtnInView() {
        ActivityScenario MainActivityTest = ActivityScenario.launch(MainActivity.class); // Lunch activity
        onView(withText("Quizzy using LiveData now")).check(matches(isDisplayed())); //Look at the title
        onView(withId(R.id.startQuiz)).check(matches(isDisplayed())); //Look at the startQuiz BTN
    }

    @Test
    public void TestNavToStartQuizBtn() {
        ActivityScenario MainActivityTest = ActivityScenario.launch(MainActivity.class); // Launch Main activity
        onView(withId(R.id.startQuiz)).perform(click()); //Look at the startQuiz BTN and click it
        onView(withId(R.id.QuestionActivityId)).check(matches(isDisplayed())); //Look at the quiz activity
    }

}