package com.mandarjoshi.nycschools.viewmodel

import com.mandarjoshi.nycschools.model.SchoolScore
import com.mandarjoshi.nycschools.model.SchoolDetails

import androidx.lifecycle.*
import com.mandarjoshi.nycschools.repo.SchoolRepository

import com.mandarjoshi.nycschools.util.Resource

private const val BEST_READING_SCORE = 400

class SchoolViewModel(private val schoolRepository: SchoolRepository) : ViewModel() {

    //since caching is not implemented, it is cached here
    private var schoolList: List<SchoolDetails>? = null
    private var schoolScores: List<SchoolScore>? = null

    var scoreMap: Map<String, SchoolScore> = HashMap()

    fun getSchoolList(): LiveData<Resource<List<SchoolDetails>?>> {
        if (schoolList == null) {
            return schoolRepository.getSchoolList()
        }
        return liveData { emit(Resource.success(schoolList)) }
    }

    fun getSchoolScore(): LiveData<Resource<List<SchoolScore>?>> {
        if (schoolScores == null) {
            return schoolRepository.getSchoolScores()
        }
        return liveData { emit(Resource.success(schoolScores)) }
    }

    fun isReadingBestScore(schoolScore: SchoolScore?): Boolean {
        if (isNumber(schoolScore?.avgScoreReading)) {
            schoolScore?.avgScoreReading?.let {
                return it.toInt() > BEST_READING_SCORE
            }
        }
        return false
    }

    fun refreshSchoolList(list: Resource<List<SchoolDetails>?>) {
        schoolList = list.data
    }

    fun refreshSchoolScores(scores: Resource<List<SchoolScore>?>) {
        schoolScores = scores.data
        scoreMap = getScoreMap(scores.data)
    }

    private fun getScoreMap(list: List<SchoolScore>?): Map<String, SchoolScore> {
        val map: MutableMap<String, SchoolScore> = HashMap()
        if (list != null && list.isNotEmpty()) for (score in list) {
            map[score.databaseNumber] = score
        }
        return map
    }

    private fun isNumber(s: String?): Boolean {
        return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
    }
}
