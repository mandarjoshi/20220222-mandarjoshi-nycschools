package com.mandarjoshi.nycschools

import android.app.Application
import com.mandarjoshi.nycschools.di.ApplicationComponent
import com.mandarjoshi.nycschools.di.DaggerApplicationComponent

class NycSchoolApplication: Application() {
    var appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}
