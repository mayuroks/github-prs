package com.mayur.naviassignment.di

import com.mayur.naviassignment.data.pulls.PullsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {

    @Provides
    fun createPullsService(retrofit: Retrofit): PullsService {
        return retrofit.create(PullsService::class.java)
    }
}