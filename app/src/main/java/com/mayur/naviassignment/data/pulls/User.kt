package com.mayur.naviassignment.data.pulls

import com.google.gson.annotations.SerializedName


class User(
    val avatarUrl: String,

    @SerializedName("login")
    val username: String
)