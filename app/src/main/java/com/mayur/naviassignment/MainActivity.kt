package com.mayur.naviassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.ui.home.HomeUI
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PullsViewModel by viewModels()
    private val sdf by lazy { SimpleDateFormat("d MMMM yy") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            HomeUI(
                viewModel = viewModel,
                sdf = sdf
            )
        }
    }
}
