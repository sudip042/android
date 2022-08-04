package com.example.bookcarservicing.Controllers;

import androidx.test.rule.ActivityTestRule;

import com.example.bookcarservicing.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegistrationTest {

    @Rule
    public ActivityTestRule<Registration> RegistrationActivityTestRule=
            new ActivityTestRule<>(Registration.class);
    Registration registration = null;
    @Before
    public void setUp() throws Exception {
        registration = RegistrationActivityTestRule.getActivity();
    }

    @Test
    public void test() throws Exception{
        onView(withId(R.id.et_name))
                .perform(typeText("rabin"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.et_email))
                .perform(typeText("rabin12@gmail.com"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_phonenumber))
                .perform(typeText("980764345678"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_address))
                .perform(typeText("ktm"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_username))
                .perform(typeText("rabin"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_password))
                .perform(typeText("rabin"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_cnfpassword))
                .perform(typeText("rabin"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btn_register))
                .perform(click());


    }

    @After
    public void tearDown() throws Exception {
    }
}