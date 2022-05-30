package com.mayur.naviassignment.pulls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mayur.naviassignment.data.pulls.PullsRepository
import kotlinx.coroutines.launch

class PullsViewModel(private val repository: PullsRepository): ViewModel() {

    fun getPulls() {
        viewModelScope.launch {
            with(repository.getPulls()) {
                when {
                    isSuccess() -> {
                        println(
                            "result?.createdAt ${result?.getOrNull(0)?.createdAt}" +
                                    " result?.user ${result?.getOrNull(0)?.user}"
                        )
                    }
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