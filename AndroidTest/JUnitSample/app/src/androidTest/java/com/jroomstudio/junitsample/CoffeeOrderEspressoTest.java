package com.jroomstudio.junitsample;



import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CoffeeOrderEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void testChangeTextToSecondActivity() throws Exception {
        //커피 수량 증가 버튼 클릭
        Espresso.onView(withId(R.id.coffee_increment)).perform(click());
        //COUNT 가 1인지 확인
        Espresso.onView(withId(R.id.coffee_count)).check(matches(withText("1")));
        //액티비티 이동 버튼 클릭
        Espresso.onView(withId(R.id.button_move)).perform(click());
    }
}
