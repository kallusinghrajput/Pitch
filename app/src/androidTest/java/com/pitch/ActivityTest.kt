package com.pitch

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pitch.ui.activities.authantication.AuthenticationActivity
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.fragments.home.HomeFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {

    /**automatically closed test when test is
    completed*/
    @get:Rule
    var activitySenerio = activityScenarioRule<AuthenticationActivity>()


    @Test
    fun testActivity() {
        /*launchActivity<HomeActivity>().use {
   identify the activity launch successfully and not
   but this is not automatically close the activity after the test
   so your test stability is not improved
           }*/
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val scenario = activitySenerio.scenario
        scenario.onActivity { activity ->
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(activity, intent, null)
        }
    }


    fun navigationTest() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val navController = TestNavHostController(context)

        val titleSanario = launchFragmentInContainer<HomeFragment>()

        titleSanario.onFragment {
            navController.setGraph(R.navigation.home)
            Navigation.setViewNavController(it.requireView(), navController)
        }
        assert(navController.currentDestination!!.id == R.id.loginFragment)
    }
}