package com.glucode.about_you.about.views

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class QuestionCardViewTest {

    private lateinit var questionCardView: QuestionCardView
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        questionCardView = QuestionCardView(context)
    }

    @Test
    fun testQuestionCardView() {
        assertNotNull(questionCardView)
    }
}