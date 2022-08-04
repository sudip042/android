package com.example.bookcarservicing;

import android.support.test.runner.AndroidJUnit4;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.example.bookcarservicing.Controllers.Registration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTest {

    @Rule
    public ActivityTestRule<Registration> RegistrationActivityTestRule=
            new ActivityTestRule<>(Registration.class);

    @Test
    public void TestSignup() throws Exception{
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
}
