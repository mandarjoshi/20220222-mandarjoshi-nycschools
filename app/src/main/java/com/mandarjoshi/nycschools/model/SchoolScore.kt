package com.mandarjoshi.nycschools.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchoolScore(
    @Json(name = "dbn") var databaseNumber: String,
    @Json(name = "school_name") var schoolName: String,
    @Json(name = "num_of_sat_test_takers") var numOfTakers: String,
    @Json(name = "sat_critical_reading_avg_score") var avgScoreReading: String,
    @Json(name = "sat_math_avg_score") var avgScoreMath: String,
    @Json(name = "sat_writing_avg_score") var avgScoreWriting: String
)
