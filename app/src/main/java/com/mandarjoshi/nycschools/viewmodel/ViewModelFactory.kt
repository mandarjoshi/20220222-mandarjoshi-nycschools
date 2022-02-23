package com.mandarjoshi.nycschools.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.mandarjoshi.nycschools.repo.SchoolRepository

import androidx.lifecycle.ViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val schoolRepository: SchoolRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(aClass: Class<T>): T {
        return SchoolViewModel(schoolRepository) as T
    }
}
