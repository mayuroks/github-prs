package com.mayur.naviassignment.ui.pulls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.ui.pulls.PullsPagingSource.Companion.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullsViewModel @Inject constructor(
    private val pagingSource: PullsPagingSource
) : ViewModel() {

    private var closedPRRequest = ClosedPRRequest("", "", "")

    var pulls: Flow<PagingData<PullRequest>> =
        Pager(PagingConfig(ITEMS_PER_PAGE)) {
            pagingSource.apply {
                viewModelScope.launch { updateQueryParams(closedPRRequest) }
            }
        }.flow

    fun setQueryParams(owner: String, repo: String, state: String) {
        closedPRRequest = ClosedPRRequest(owner, repo, state)
        pulls = Pager(PagingConfig(ITEMS_PER_PAGE)) {
            pagingSource.apply {
                viewModelScope.launch { updateQueryParams(closedPRRequest) }
            }
        }.flow
    }

    class Factory(
        private val pagingSource: PullsPagingSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PullsViewModel(pagingSource) as T
        }
    }
}