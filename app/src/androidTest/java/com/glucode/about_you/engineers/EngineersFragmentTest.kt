package com.glucode.about_you.engineers

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glucode.about_you.R
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EngineersFragmentTest {

    private lateinit var scenario: FragmentScenario<EngineersFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AboutYou)
    }

    @Test
    fun testEngineersFragment() {
        scenario.onFragment { fragment ->
            assertNotNull(fragment)
        }
    }

    @Test
    fun testRecyclerView() {
        scenario.onFragment { fragment ->
            val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.list)
            assertNotNull(recyclerView)
            assertEquals(View.VISIBLE, recyclerView?.visibility)
        }
    }
}