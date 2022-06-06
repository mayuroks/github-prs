package com.mayur.naviassignment.ui.pulls

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import javax.inject.Inject

class PullsPagingSource @Inject constructor(
    private val repository: PullsRepository
) : PagingSource<Int, PullRequest>() {
    var owner = "docker-library"
    var repo = "golang"
    var state = "closed"

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        println("PagingSource getRefreshKey called")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {

//        if (owner.isBlank() or repo.isBlank() or state.isBlank()) {
//            return LoadResult.Error(Exception("Some data is empty"))
//        }

        val nextPage = params?.key ?: 1
        println("PagingSource load called nextPage $nextPage")
        val response = repository.getPulls(owner, repo, state, nextPage)
        return if (response.isSuccess() && response.result != null) {
            LoadResult.Page(
                data = response.result,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } else {
            LoadResult.Error(
                Exception("Some data is empty")
            )
        }
    }

    fun updateRepoParas(owner: String, repo: String, state: String) {
        this.owner = owner
        this.repo = repo
        this.state = state
    }
}