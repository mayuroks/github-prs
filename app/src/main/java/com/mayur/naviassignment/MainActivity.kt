package com.mayur.naviassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.mayur.naviassignment.closedpulls.ClosePullsUI
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.data.RepoProvider
import com.mayur.naviassignment.pulls.PullsViewModel
import com.mayur.naviassignment.ui.theme.NaviAssignmentTheme
import java.text.SimpleDateFormat

// Pass auth token and make api call
// Create Auth interceptor
// Circle Avatar
// beautify card
// Date format

class MainActivity : ComponentActivity() {
    val sdf = SimpleDateFormat("d MMMM yy")

    private val viewModel by viewModels<PullsViewModel> {
        PullsViewModel.Factory(RepoProvider.pullsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            MainUI(
                viewModel = viewModel,
                sdf = sdf
            )
        }

    }
}

@Composable
fun MainUI(viewModel: PullsViewModel, sdf: SimpleDateFormat) {
    val pulls by viewModel.pulls

    LaunchedEffect(key1 = Unit) {
        viewModel.getPulls()
    }

    pulls?.let {
        ClosePullsUI(it, sdf)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NaviAssignmentTheme {
        Greeting("Android")
    }
}