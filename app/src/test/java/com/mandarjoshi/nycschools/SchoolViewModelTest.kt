package com.mandarjoshi.nycschools

import android.text.TextUtils

import com.mandarjoshi.nycschools.model.SchoolScore
import com.mandarjoshi.nycschools.repo.SchoolRepository
import com.mandarjoshi.nycschools.viewmodel.SchoolViewModel
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue

import org.junit.After

import org.junit.Before
import org.junit.Test


class SchoolViewModelTest {
    /*
    val schoolRepository: SchoolRepository = mock()
    private val viewModel: SchoolViewModel = SchoolViewModel(schoolRepository)
    private lateinit var mocked: MockedStatic<TextUtils>

    @Before
    fun setUp() {
        mocked = mockStatic(TextUtils::class.java)
    }

    @After
    fun close() {
        mocked.close()
    }

    @Test
    fun testIsReadingBestScore_when_null() {
        assertFalse(viewModel.isReadingBestScore(null))
    }

    @Test
    fun testIsReadingBestScore_when_score_not_digit() {
        val schoolScore = getSchoolScore()
        schoolScore.avgScoreReading = "s"
        `when`(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(false)
        assertFalse(viewModel.isReadingBestScore(schoolScore))
    }

    @Test
    fun testIsReadingBestScore_score_401() {
        val schoolScore = getSchoolScore()
        getSchoolScore().avgScoreReading = "401"
        `when`(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(true)
        assertTrue(viewModel.isReadingBestScore(schoolScore))
    }

    @Test
    fun testIsReadingBestScore_score_399() {
        val schoolScore = getSchoolScore()
        schoolScore.avgScoreReading = "399"
        `when`(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(true)
        assertFalse(viewModel.isReadingBestScore(schoolScore))
    }

    private fun getSchoolScore() = SchoolScore("databaseNumber",
        "schoolName", "5", "100",
        "100", "100")
*/
}
