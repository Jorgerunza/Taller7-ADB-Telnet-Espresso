package com.fsck.k9.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.fsck.k9.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class K9EnvioEmailOK {

    @Rule
    public ActivityTestRule<Accounts> mActivityTestRule = new ActivityTestRule<>(Accounts.class);

    @Test
    public void k9Auth() {
        ViewInteraction button = onView(
                allOf(withId(R.id.next), withText("Next"), isDisplayed()));
        button.perform(click());

        ViewInteraction editText = onView(
                withId(R.id.account_email));
        editText.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(
                withId(R.id.account_email));
        editText2.perform(scrollTo(), replaceText("misotest2017@gmail.com"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                withId(R.id.account_password));
        editText3.perform(scrollTo(), replaceText("pruebas2017*"), closeSoftKeyboard());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.show_password), withText("Show password")));
        checkBox.perform(scrollTo(), click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.next), withText("Next"), isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(android.R.id.button2), withText("Continue"), isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(android.R.id.button2), withText("Continue"), isDisplayed()));
        button4.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.account_description), withContentDescription("Give this account a name (optional):")));
        editText4.perform(scrollTo(), click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.account_description), withContentDescription("Give this account a name (optional):")));
        editText5.perform(scrollTo(), replaceText("miso"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.account_name), withContentDescription("Type your name (displays on outgoing messages):")));
        editText6.perform(scrollTo(), replaceText("2017"), closeSoftKeyboard());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.done), withText("Done"), isDisplayed()));
        button5.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        button6.perform(click());



    }


    @Test
    public void k9EnvioEmailOK() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.compose), withContentDescription("Compose"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction recipientSelectView = onView(
                allOf(withId(R.id.to),
                        withParent(withId(R.id.to_wrapper))));
        recipientSelectView.perform(scrollTo(), replaceText("jorged_51@hotmail.com"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                withId(R.id.subject));
        editText7.perform(scrollTo(), replaceText("Prueba"), closeSoftKeyboard());

        ViewInteraction eolConvertingEditText = onView(
                withId(R.id.message_content));
        eolConvertingEditText.perform(scrollTo(), replaceText("Prueba"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.send), withContentDescription("Send"), isDisplayed()));
        actionMenuItemView2.perform(click());

    }

}
