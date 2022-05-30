package com.mayur.naviassignment.data

import com.mayur.naviassignment.data.pulls.PullsService


object ApiServiceProvider {
    val pullsService by lazy { create<PullsService>() }
}