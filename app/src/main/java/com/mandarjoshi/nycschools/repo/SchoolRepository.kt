package com.mandarjoshi.nycschools.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mandarjoshi.nycschools.model.SchoolDetails

import com.mandarjoshi.nycschools.model.SchoolScore
import com.mandarjoshi.nycschools.util.Resource
import kotlinx.coroutines.Dispatchers

open class SchoolRepository(private val schoolService: SchoolService) {

    fun getSchoolScores(): LiveData<Resource<List<SchoolScore>?>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data=schoolService.getSchoolScores()))
            }catch (exception: Exception){
                emit(Resource.error(data=null,message = "Error"))
            }
            //catching high level exception for now
            // detekt static code analysis is catching it.
        }
    }

    fun getSchoolList(): LiveData<Resource<List<SchoolDetails>?>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            try {
                emit(Resource.success(data=schoolService.getSchoolList()))
            }catch (exception: Exception){
                emit(Resource.error(data=null,message = "Error"))
            }
        }
    }
}
