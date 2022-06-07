package com.mayur.naviassignment.ui.pulls

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import javax.inject.Inject

class PullsPagingSource @Inject constructor(
    private val repository: PullsRepository
) : PagingSource<Int, PullRequest>() {

    private var closedPRRequest = ClosedPRRequest("", "")

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        if (closedPRRequest.isBlank())
            return LoadResult.Error(Exception("Some data is empty"))

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
                else -> LoadResult.Error(Exception("Some data is empty"))
            }
        }
    }

    suspend fun updateQueryParams(closedPRRequest: ClosedPRRequest) {
        this.closedPRRequest = closedPRRequest
        val loadParams = LoadParams.Refresh(0, ITEMS_PER_PAGE, false)
        load(loadParams)
    }

    companion object {
        const val ITEMS_PER_PAGE = 40
    }
}