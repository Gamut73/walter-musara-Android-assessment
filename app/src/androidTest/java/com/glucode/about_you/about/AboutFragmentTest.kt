package com.glucode.about_you.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glucode.about_you.R
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutFragmentTest {

    private lateinit var scenario: FragmentScenario<AboutFragment>

    @Before
    fun setup() {
        val fragmentArgs = Bundle().apply {
            putString("name", "Wilmar")
        }
        scenario = launchFragmentInContainer(fragmentArgs = fragmentArgs, themeResId = R.style.Theme_AboutYou)
    }

    @Test
    fun testAboutFragment() {
        scenario.onFragment { fragment ->
            assertNotNull(fragment)
        }
    }

    @Test
    fun testEngineerProfile() {
        scenario.onFragment { fragment ->
            val engineerProfile = fragment.view?.findViewById<View>(R.id.engineer_profile)
            assertNotNull(engineerProfile)
            assertEquals(View.VISIBLE, engineerProfile?.visibility)
        }
    }
}