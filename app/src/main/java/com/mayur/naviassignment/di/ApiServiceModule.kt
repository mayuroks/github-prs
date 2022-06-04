package com.mayur.naviassignment.di

import com.mayur.naviassignment.data.create
import com.mayur.naviassignment.data.pulls.PullsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {

    @Provides
    fun createPullsService(): PullsService {
        return create<PullsService>()
    }
}