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

    class RefreshError : PagingState() {
        override fun toString(): String {
            return "Couldn't get items for your query"
        }
    }

    class RetryLoading : PagingState() {
        override fun toString(): String {
            return ""
        }
    }
}