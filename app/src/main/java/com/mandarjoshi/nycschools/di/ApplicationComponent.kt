package com.mandarjoshi.nycschools.di

import com.mandarjoshi.nycschools.ui.SchoolFragment
import com.mandarjoshi.nycschools.ui.SchoolScoresFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(schoolFragment: SchoolFragment)
    fun inject(schoolScoresFragment: SchoolScoresFragment)
}
