package com.mayur.naviassignment.ui.pulls

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import com.mayur.naviassignment.ui.pulls.PullsPagingSource.Companion.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullsViewModel @Inject constructor(
    private val repository: PullsRepository
) : ViewModel() {

    val pagingState = mutableStateOf<PagingState>(PagingState.Loading())
    val closedPRRequest = mutableStateOf(ClosedPRRequest("", "", ""))

    var pulls: Flow<PagingData<PullRequest>>? = null
//        Pager(PagingConfig(ITEMS_PER_PAGE)) {
//            pagingSource.apply {
//                viewModelScope.launch { updateQueryParams(closedPRRequest.value) }
//            }
//        }.flow

    fun getClosedPRs(request: ClosedPRRequest) {
        closedPRRequest.value = request
        val pagingSource = PullsPagingSource_Factory.newInstance(repository)
        pagingSource.apply {
            viewModelScope.launch { updateQueryParams(closedPRRequest.value) }
        }
        pulls = Pager(PagingConfig(ITEMS_PER_PAGE)) {
            pagingSource
        }.flow
    }

    fun handlePagingLoading() {
        pagingState.value = PagingState.Loading()
    }

    fun handlePagingAppendError() {
        pagingState.value = PagingState.AppendError()
    }

    fun handlePagingRefreshError() {
        pagingState.value = PagingState.RefreshError()
    }

    fun forceRefresh() {
        pagingState.value = PagingState.RetryLoading()
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
