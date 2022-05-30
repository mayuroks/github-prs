package com.mayur.naviassignment.data.pulls

import com.google.gson.annotations.SerializedName
import java.util.*

class PullRequest(
    val id: Long,
    val title: String,
    val user: User,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("closed_at")
    val closedAt: Date?
)

enum class PullState {
    closed, open, all
}