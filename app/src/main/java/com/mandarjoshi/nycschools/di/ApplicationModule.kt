package com.mandarjoshi.nycschools.di

import com.mandarjoshi.nycschools.repo.SchoolService
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import com.mandarjoshi.nycschools.repo.SchoolRepository
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory


@Module
class ApplicationModule {
    @Provides
    fun providesSchoolService(
        retrofit: Retrofit
    ): SchoolService {
        return retrofit.create(SchoolService::class.java)
    }

    @Provides
    fun providesSchoolRepository(schoolService: SchoolService): SchoolRepository {
        return SchoolRepository(schoolService)
    }

    @Provides
    fun providesViewModelFactory(schoolRepository: SchoolRepository): ViewModelFactory {
        return ViewModelFactory(schoolRepository)
    }
}
