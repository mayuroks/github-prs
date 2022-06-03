package com.mayur.naviassignment.data.pulls

import com.google.gson.annotations.SerializedName


class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("login")
    val username: String
)