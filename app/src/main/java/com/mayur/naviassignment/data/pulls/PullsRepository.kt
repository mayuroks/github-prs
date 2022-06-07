package com.mayur.naviassignment.data.pulls

import com.mayur.naviassignment.data.AsyncResult
import com.mayur.naviassignment.data.apiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullsRepository @Inject constructor(private val pullsService: PullsService) {
    suspend fun getPulls(closedPRRequest: ClosedPRRequest, page: Int): AsyncResult<List<PullRequest>> {
        return apiCall {
            with(closedPRRequest) {
                pullsService.getPulls(owner, repo, state, page)
            }
        }
    }
}