package com.jroomstudio.unittestuitest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * onView : View 처리 (TestView, EditText, Button 등)
 * withText : 해당 텍스트 "Hello world!" 를 가지는 View 를 찾는다.
 * withId : R.id.view 에 해당하는 View 를 찾는다.
 * perform : ViewAction 을 처리한다.
 * click : 아이템을 클릭하는 이벤트를 처리한다.
 * check(matches()) : ViewAssertion 유효성을 체크하고, 맞는지 확인한다.
 * isDisplayed : 화면에 노출상태를 가져온다.
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivity
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    /**
     * Espresso HelloWorld
     **/
    @Test
    public void listGoesOverTheFold() {
        // withText 를 통해 HelloWorld 를 가진 View 를 찾고 화면에 표시 되었는지 확인한다.
        onView(withText("Hello world!")).check(matches(isDisplayed()));
        // withId 를 통해 R.id.view 를 찾고 화면에 표시되었는지 확인한다.
        onView(withId(R.id.view)).check(matches(isDisplayed()));
        // withId 를 통해 뷰를 찾고 클릭 이벤트를 처리한다.
        onView(withId(R.id.view)).perform(click());
    }

    /**
     * EditText, Button Click Event
     * Activity 2개를 이용하여 EditText 의 데이터를 전달하는 예제이다.
     **/
    @Test
    public void testChangeTextToSecondActivity() throws Exception {
        // Type text and then press the button.

        // et_message 에 clearText() 를하여 값을 지운다.
        onView(withId(R.id.et_message)).perform(clearText());
        // et_message 에 typeText 를 입력하고 키보드를 숨긴다.
        onView(withId(R.id.et_message)).perform(typeText("Change Activity ..."), closeSoftKeyboard());
        // btn_second_activity 를 눌러 Intent 를 전달한다.
        onView(withId(R.id.btn_second_activity)).perform(click());
        // 두번째 액티비티 tv_message 에 값이 잘 전달되었는지 확인한다.
        onView(withId(R.id.tv_message)).check(matches(withText("Change Activity ...")));
    }

}
