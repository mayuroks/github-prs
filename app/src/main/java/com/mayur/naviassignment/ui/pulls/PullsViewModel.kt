package com.mayur.naviassignment.ui.pulls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullsViewModel @Inject constructor(
    private val repository: PullsRepository,
    private val pagingSource: PullsPagingSource
): ViewModel() {

    // TODO convert to object
    // Pass default from UI
    var owner = "docker-library"
    var repo = "golang"
    var state = "closed"

    var pulls: Flow<PagingData<PullRequest>> =
        Pager(PagingConfig(ITEMS_PER_PAGE)) { pagingSource.apply {
            viewModelScope.launch { updateQueryParams(owner, repo, state) }
        } }.flow

    fun updateQueryParams(owner: String, repo: String, state: String) {
        pulls = Pager(PagingConfig(ITEMS_PER_PAGE)) { pagingSource.apply {
            viewModelScope.launch { updateQueryParams(owner, repo, state) }
        } }.flow
    }

    class Factory(
        private val repository: PullsRepository,
        private val pagingSource: PullsPagingSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PullsViewModel(repository, pagingSource) as T
        }
    }

    companion object {
        const val ITEMS_PER_PAGE = 40
    }
}