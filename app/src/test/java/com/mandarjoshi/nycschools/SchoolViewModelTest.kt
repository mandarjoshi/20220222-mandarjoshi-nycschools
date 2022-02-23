package com.mandarjoshi.nycschools

import com.mandarjoshi.nycschools.model.SchoolScore
import com.mandarjoshi.nycschools.repo.SchoolRepository
import com.mandarjoshi.nycschools.viewmodel.SchoolViewModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

import org.junit.Test


class SchoolViewModelTest {

    private val schoolRepository: SchoolRepository = mock()
    private val viewModel: SchoolViewModel = SchoolViewModel(schoolRepository)


    @Test
    fun testIsReadingBestScore_when_null() {
        (viewModel.isReadingBestScore(null))
    }

    @Test
    fun testIsReadingBestScore_when_score_not_digit() {
        val schoolScore = getSchoolScore()
        schoolScore.avgScoreReading = "s"
        assertFalse(viewModel.isReadingBestScore(schoolScore))
    }

    @Test
    fun testIsReadingBestScore_score_401() {
        val schoolScore = getSchoolScore()
        schoolScore.avgScoreReading = "401"
        assertTrue(viewModel.isReadingBestScore(schoolScore))
    }

    @Test
    fun testIsReadingBestScore_score_399() {
        val schoolScore = getSchoolScore()
        schoolScore.avgScoreReading = "399"
        assertFalse(viewModel.isReadingBestScore(schoolScore))
    }

    private fun getSchoolScore() = SchoolScore("databaseNumber",
        "schoolName", "5", "100",
        "100", "100")

}
