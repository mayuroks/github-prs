package com.mayur.naviassignment.data.pulls

import com.mayur.naviassignment.data.AsyncResult
import com.mayur.naviassignment.data.apiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullsRepository @Inject constructor(private val pullsService: PullsService) {
    suspend fun getPulls(owner: String, repo: String, state: String, page: Int): AsyncResult<List<PullRequest>> {
        return apiCall { pullsService.getPulls(owner, repo, state, page) }
    }
}