package com.glucode.about_you.about.views

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AnswerCardViewTest {

    private lateinit var answerCardView: AnswerCardView
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        answerCardView = AnswerCardView(context)
    }

    @Test
    fun testAnswerCardView() {
        assertNotNull(answerCardView)
        assertNull(answerCardView.title)
    }

    @Test
    fun testSelectedState() {
        assertFalse(answerCardView.isSelected)
        answerCardView.setSelected(true)
        assertTrue(answerCardView.isSelected)
    }

    @Test
    fun testSelectedAnswer() {
        val answer = "6am"
        answerCardView.title = answer
        assertEquals(answer, answerCardView.title)
    }
}