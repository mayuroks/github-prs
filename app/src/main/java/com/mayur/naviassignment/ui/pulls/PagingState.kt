package com.mayur.naviassignment.ui.pulls

sealed class PagingState {
    class Loading : PagingState() {
        override fun toString(): String {
            return "Loading items"
        }
    }

    class AppendError : PagingState() {
        override fun toString(): String {
            return "Couldn't load new items"
        }
    }

    class RefreshError(private val errorMsg: String) : PagingState() {
        override fun toString(): String {
            return errorMsg
        }
    }
}