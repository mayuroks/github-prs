package com.mayur.naviassignment.ui.pulls

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PullsViewModel @Inject constructor(
    private val repository: PullsRepository,
    private val pagingSource: PullsPagingSource
): ViewModel() {
//    var pulls = mutableStateOf<List<PullRequest>?>(listOf())
    var pullsIsLoading = mutableStateOf(false)
    var owner = "docker-library"
    var repo = "golang"
    var state = "closed"
    val pulls: Flow<PagingData<PullRequest>> =
        Pager(PagingConfig(40)) { pagingSource.apply {
            updateRepoParas(owner, repo, state)
        } }.flow


    fun updateRepoParas(owner: String, repo: String, state: String) {
        pagingSource.updateRepoParas(owner, repo, state)
    }
//    fun getPulls(page: Int = 1) {
//        viewModelScope.launch {
//            with(repository.getPulls(owner, repo, state, page)) {
//                when {
//                    isSuccess() -> pulls.value = result
//                    isError() ->  {}
//                    inProgress() -> {}
//                }
//            }
//        }
//    }


    class Factory(
        private val repository: PullsRepository,
        private val pagingSource: PullsPagingSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PullsViewModel(repository, pagingSource) as T
        }
    }
}