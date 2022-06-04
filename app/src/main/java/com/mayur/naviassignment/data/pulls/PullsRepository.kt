package com.mayur.naviassignment.data.pulls

import com.mayur.naviassignment.data.AsyncResult
import com.mayur.naviassignment.data.apiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullsRepository @Inject constructor(private val pullsService: PullsService) {
    suspend fun getPulls(): AsyncResult<List<PullRequest>> {
        return apiCall { pullsService.getPulls("docker-library", "golang", "closed") }
    }
}