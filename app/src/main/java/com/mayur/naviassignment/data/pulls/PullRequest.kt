package com.mayur.naviassignment.data.pulls

import com.google.gson.annotations.SerializedName
import java.util.*

class PullRequest(
    val id: Long,
    val title: String,
    val number: Long,
    val user: User,
    val head: Head,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("closed_at")
    val closedAt: Date?
)

class Head(val repo: Repo)

class Repo(
    @SerializedName("full_name")
    val fullName: String
)

enum class PullState {
    closed, open, all
}