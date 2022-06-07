package com.mayur.naviassignment.ui.pulls

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import javax.inject.Inject

class PullsPagingSource @Inject constructor(
    private val repository: PullsRepository
) : PagingSource<Int, PullRequest>() {
//    var owner = "docker-library"
//    var repo = "golang"
//    var state = "closed"

    private var owner = ""
    private var repo = ""
    private var state = ""


    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        if (owner.isBlank() or repo.isBlank() or state.isBlank()) {
            return LoadResult.Error(Exception("Some data is empty"))
        }

        val nextPage = params?.key ?: 1

        with(repository.getPulls(owner, repo, state, nextPage)) {
            return when {
                isSuccess() -> {
                    if (result != null) {
                        LoadResult.Page(
                            data = result,
                            prevKey =
                            if (nextPage == 1) null else nextPage - 1,
                            nextKey = nextPage.plus(1)
                        )
                    } else {
                        LoadResult.Error(
                            Exception("Some data is empty")
                        )
                    }
                }
                else ->
                    LoadResult.Error(Exception("Some data is empty"))
            }
        }
    }

    suspend fun updateQueryParams(owner: String, repo: String, state: String) {
        this.owner = owner
        this.repo = repo
        this.state = state

        val loadParams = LoadParams.Refresh<Int>(0, 30, false)
        load(loadParams)
    }
}