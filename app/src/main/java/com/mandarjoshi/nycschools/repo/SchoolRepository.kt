package com.mandarjoshi.nycschools.repo

import com.mandarjoshi.nycschools.model.SchoolDetails

import com.mandarjoshi.nycschools.model.SchoolScore
import retrofit2.Call

open class SchoolRepository(private val schoolService: SchoolService) {

    fun getSchoolScores(): Call<List<SchoolScore>> {
        return schoolService.getSchoolScores()
    }

    fun getSchoolList(): Call<List<SchoolDetails>> {
        return schoolService.getSchoolList()
    }
}
