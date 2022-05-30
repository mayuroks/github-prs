package com.mayur.naviassignment.data

import com.mayur.naviassignment.data.pulls.PullsRepository


object RepoProvider {
    val pullsRepository by lazy { PullsRepository(ApiServiceProvider.pullsService) }
}