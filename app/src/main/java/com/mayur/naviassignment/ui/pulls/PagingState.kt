package com.mayur.naviassignment.ui.pulls

sealed class PagingState {
    object Success: PagingState() {
        override fun toString(): String {
            return "Couldn't load new items"
        }
    }
    object AppendError: PagingState() {
        override fun toString(): String {
            return "Couldn't load new items"
        }
    }
    object RefreshError: PagingState() {
        override fun toString(): String {
            return "Couldn't get items for your query"
        }
    }
}