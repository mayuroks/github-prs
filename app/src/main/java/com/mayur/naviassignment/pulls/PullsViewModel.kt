package com.mayur.naviassignment.pulls

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.PullsRepository
import kotlinx.coroutines.launch

class PullsViewModel(private val repository: PullsRepository): ViewModel() {
    var pulls = mutableStateOf<List<PullRequest>?>(listOf())
    var pullsIsLoading = mutableStateOf(false)

    fun getPulls() {
        viewModelScope.launch {
            with(repository.getPulls()) {
                when {
                    isSuccess() -> pulls.value = result
                    isError() ->  {}
                    inProgress() -> {}
                }
            }
        }
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