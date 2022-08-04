package com.example.bookcarservicing;

import android.support.test.runner.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import com.example.bookcarservicing.Controllers.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
class logintest {
    @Rule
    public ActivityTestRule<Login> activity_loginActivityTestRule = new ActivityTestRule<>(Login.class);

    //    public ActivityTestRule<Activity_login> activityTestRule = new ActivityTestRule<>(Activity_login.class);
//
    @Test
    public void logintest() {
        onView(withId(R.id.et_username)).perform(typeText("sagar"));
        closeSoftKeyboard();
        onView(withId(R.id.et_password)).perform(typeText("sagar"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
    }
}


