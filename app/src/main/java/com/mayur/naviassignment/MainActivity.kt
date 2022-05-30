package com.mayur.naviassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.data.RepoProvider
import com.mayur.naviassignment.pulls.PullsViewModel
import com.mayur.naviassignment.ui.theme.NaviAssignmentTheme

// Pass auth token and make api call
// Create Auth interceptor
//

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<PullsViewModel> {
        PullsViewModel.Factory(RepoProvider.pullsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            ClosePullsUI(viewModel = viewModel)
        }
    }
}

@Composable
fun ClosePullsUI(viewModel: PullsViewModel) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getPulls()
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