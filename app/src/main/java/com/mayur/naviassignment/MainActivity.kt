package com.mayur.naviassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.ui.pulls.ClosePullsUI
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import com.mayur.naviassignment.ui.theme.NaviAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

// TODO
// beautify card (text style and theme colors)
// show data based on progress etc.
// pagination
// use flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PullsViewModel by viewModels()
    private val sdf by lazy { SimpleDateFormat("d MMMM yy") }

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
    var txt = ""

    LaunchedEffect(key1 = Unit) {
        viewModel.getPulls()
    }

    pulls?.getOrNull(0)?.head?.repo?.fullName?.let {
        txt = "Showing closed pulls requests for $it"
    } ?: run { txt = "No closed pulls found" }

    Column {
        Text(
            text = txt,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 0.dp)
        )
        Spacer(modifier = Modifier.padding(12.dp))

        pulls?.let {
            ClosePullsUI(it, sdf)
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