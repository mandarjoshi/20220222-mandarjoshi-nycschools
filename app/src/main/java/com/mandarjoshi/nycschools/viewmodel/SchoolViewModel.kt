package com.mandarjoshi.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import com.mandarjoshi.nycschools.model.SchoolScore
import com.mandarjoshi.nycschools.model.SchoolDetails

import androidx.lifecycle.MutableLiveData

import android.text.TextUtils
import android.util.Log
import com.mandarjoshi.nycschools.repo.SchoolRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.lifecycle.LiveData

private const val BEST_READING_SCORE = 400

class SchoolViewModel(private val schoolRepository: SchoolRepository) : ViewModel() {

    private var schoolList= MutableLiveData<List<SchoolDetails>?>()
    private var schoolScores = MutableLiveData<List<SchoolScore>?>()

    var scoreMap: Map<String, SchoolScore> = HashMap()

    fun getSchoolList(): LiveData<List<SchoolDetails>?> {
        if (schoolList.value == null) {
            populateSchoolList()
        }
        return schoolList
    }

    fun getSchoolScore(): LiveData<List<SchoolScore>?> {
        if (schoolScores.value == null) {
            populateSchoolScores()
        }
        return schoolScores
    }

    fun isReadingBestScore(schoolScore: SchoolScore?): Boolean {
        if (schoolScore != null) {
            if (TextUtils.isDigitsOnly(schoolScore.avgScoreReading)) {
                return schoolScore.avgScoreReading.toInt() > BEST_READING_SCORE
            }
        }
        return false
    }

    private fun populateSchoolList() {
        val call: Call<List<SchoolDetails>> = schoolRepository.getSchoolList()
        call.enqueue(object : Callback<List<SchoolDetails>?> {
            override fun onResponse(
                call: Call<List<SchoolDetails>?>,
                response: Response<List<SchoolDetails>?>
            ) {
                val list = response.body()
                schoolList.postValue(list)
            }

            override fun onFailure(call: Call<List<SchoolDetails>?>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.e("API error :: ", it)
                }
                schoolList.postValue(null)
            }
        })
    }

    private fun populateSchoolScores() {
        val call: Call<List<SchoolScore>> = schoolRepository.getSchoolScores()
        call.enqueue(object : Callback<List<SchoolScore>?> {
            override fun onResponse(
                call: Call<List<SchoolScore>?>,
                response: Response<List<SchoolScore>?>
            ) {
                val scores = response.body()
                refreshSchoolScores(scores)
            }

            override fun onFailure(call: Call<List<SchoolScore>?>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.e("API error :: ", it)
                }
                refreshSchoolScores(null)
            }
        })
    }

    private fun refreshSchoolScores(scores: List<SchoolScore>?) {
        schoolScores.postValue(scores)
        scoreMap = getScoreMap(scores)
    }

    private fun getScoreMap(list: List<SchoolScore>?): Map<String, SchoolScore> {
        val map: MutableMap<String, SchoolScore> = HashMap()
        if (list != null && list.isNotEmpty()) for (score in list) {
            map[score.databaseNumber] = score
        }
        return map
    }
}