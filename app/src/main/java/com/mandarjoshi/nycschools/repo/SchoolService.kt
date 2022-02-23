package com.mandarjoshi.nycschools.repo

import com.mandarjoshi.nycschools.model.SchoolDetails

import retrofit2.http.GET

import com.mandarjoshi.nycschools.model.SchoolScore
import retrofit2.Call


interface SchoolService {
    @GET("resource/f9bf-2cp4.json")
    fun getSchoolScores(): Call<List<SchoolScore>>

    @GET("resource/s3k6-pzi2.json")
    fun getSchoolList(): Call<List<SchoolDetails>>
}
