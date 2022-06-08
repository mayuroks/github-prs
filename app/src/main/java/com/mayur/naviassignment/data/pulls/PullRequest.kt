package com.mayur.naviassignment.data.pulls

import com.google.gson.annotations.SerializedName
import java.util.*

class PullRequest(
    val title: String,
    val number: Long,
    val user: User,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("closed_at")
    val closedAt: Date?
)

class ClosedPRRequest(
    val owner: String,
    val repo: String,
    val state: String = CLOSED
) {
    fun isBlank() = owner.isBlank() or repo.isBlank() or state.isBlank()
}

const val CLOSED = "closed"
const val OPEN = "open"
const val ALL = "all"
