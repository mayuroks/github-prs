package com.mayur.naviassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.data.RepoProvider
import com.mayur.naviassignment.data.pulls.PullRequest
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
            ClosePullsUI(
                viewModel = viewModel,
                sdf = sdf
            )
        }

    }
}

@Composable
fun ClosePullsUI(viewModel: PullsViewModel, sdf: SimpleDateFormat) {
    val pulls by viewModel.pulls

    LaunchedEffect(key1 = Unit) {
        viewModel.getPulls()
    }

    pulls?.let {
        ClosePullsUI(it, sdf)
    }
}

@Composable
fun ClosePullsUI(pulls: List<PullRequest>, sdf: SimpleDateFormat) {
    LazyColumn {
        items(pulls) { pull ->
            ClosePullUI(pull, sdf)
        }
    }
}

@Composable
fun ClosePullUI(pull: PullRequest, sdf: SimpleDateFormat) {
    Card {
        Column {
            Text(text = pull.title)
            Text(text = sdf.format(pull.createdAt))
            Text(text = sdf.format(pull.closedAt ?: ""))
            Text(text = pull.user.username)
            // Glide image
            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
        }
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