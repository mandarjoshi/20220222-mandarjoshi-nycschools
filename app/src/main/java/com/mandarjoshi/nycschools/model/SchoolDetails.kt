package com.mandarjoshi.nycschools.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchoolDetails(
    @Json(name = "dbn") var databaseNumber: String,
    @Json(name = "school_name") var schoolName: String,
    @Json(name = "neighborhood") var neighbourHood: String
)
