package com.mandarjoshi.nycschools.di

import com.mandarjoshi.nycschools.BuildConfig
import dagger.Module
import retrofit2.converter.moshi.MoshiConverterFactory

import com.squareup.moshi.Moshi

import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {
    @Provides
    fun providesRetrofit(
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun providesMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }
}
