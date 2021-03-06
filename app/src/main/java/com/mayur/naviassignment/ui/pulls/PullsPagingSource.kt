package com.mayur.naviassignment.ui.pulls

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository

class PullsPagingSource(
    private val repository: PullsRepository,
    private val closedPRRequest: ClosedPRRequest
) : PagingSource<Int, PullRequest>() {

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        if (closedPRRequest.isBlank())
            return LoadResult.Error(Exception("Incorrect input. Try entering \"vuejs/vue\" without the quotes"))

        val nextPage = params.key ?: 1
        with(repository.getPulls(closedPRRequest, nextPage)) {
            return when {
                isSuccessNonNull() -> {
                    LoadResult.Page(
                        data = this.result!!,
                        prevKey =
                        if (nextPage == 1) null else nextPage - 1,
                        nextKey = nextPage.plus(1)
                    )
                }
                isError() -> LoadResult.Error(Exception(errorMessage))
                else -> LoadResult.Error(Exception("Unknown error. Try after sometime"))
            }
        }
    }

    companion object {
        const val ITEMS_PER_PAGE = 40
    }
}