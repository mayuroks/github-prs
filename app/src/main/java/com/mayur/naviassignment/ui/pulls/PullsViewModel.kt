package com.mayur.naviassignment.ui.pulls

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import com.mayur.naviassignment.ui.pulls.PullsPagingSource.Companion.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PullsViewModel @Inject constructor(
    private val repository: PullsRepository
) : ViewModel() {

    val searchText = mutableStateOf<String>("freeCodeCamp/freeCodeCamp")
    val pagingState = mutableStateOf<PagingState>(PagingState.Loading())
    val pulls = mutableStateOf<Flow<PagingData<PullRequest>>?>(null)
    private val closedPRRequest = mutableStateOf(ClosedPRRequest("", "", ""))

    fun getClosedPRs() {
        // Prevent multiple api calls for same input
        // if already api call for same input is in progress
        if (searchText.value == closedPRRequest.value.getFullRepoName()
            && pagingState.value is PagingState.Loading
        ) return

        val _tmp = searchText.value.split("/")
        if (_tmp.size != 2) {
            handlePagingRefreshError("Incorrect input. Try entering \"vuejs/vue\" without the quotes")
        } else {
            closedPRRequest.value = ClosedPRRequest(_tmp[0], _tmp[1])
            val pagingSource = PullsPagingSource(repository, closedPRRequest.value)
            pulls.value = Pager(PagingConfig(ITEMS_PER_PAGE)) {
                pagingSource
            }.flow
            handlePagingLoading()
        }
    }

    fun handlePagingLoading() {
        pagingState.value = PagingState.Loading()
    }

    fun handlePagingAppendError() {
        pagingState.value = PagingState.AppendError()
    }

    fun handlePagingRefreshError(errorMsg: String) {
        pagingState.value = PagingState.RefreshError(errorMsg)
    }

    class Factory(
        private val repository: PullsRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PullsViewModel(repository) as T
        }
    }
}
