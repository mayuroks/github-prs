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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mayur.naviassignment.components.setContentWithAppTheme
import com.mayur.naviassignment.ui.pulls.ClosePullsUI
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import com.mayur.naviassignment.ui.theme.NaviAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

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
    val pulls = viewModel.pulls.collectAsLazyPagingItems()

    // TODO Test code to refresh paged list
//    LaunchedEffect(key1 = Unit) {
//        delay(2000)
//        viewModel.setQueryParams("freeCodeCamp", "freeCodeCamp", "closed")
//
//        delay(2000)
//        viewModel.setQueryParams("996icu", "996.ICU", "closed")
//
//        delay(2000)
//        viewModel.setQueryParams("vuejs", "vue", "closed")
//    }

    LaunchedEffect(key1 = Unit) {
        viewModel.setQueryParams("freeCodeCamp", "freeCodeCamp", "closed")
    }

    Column {
        Spacer(modifier = Modifier.padding(12.dp))

        when {
            pulls.loadState.append is LoadState.Loading -> {
                println("pulls.loadState.append is LoadState.Loading")
            }
            pulls.loadState.refresh is LoadState.Loading -> {
                println("pulls.loadState.refresh is LoadState.Loading")
            }
            pulls.loadState.append is LoadState.Error -> {
                println("pulls.loadState.append is LoadState.Error")
            }
            pulls.loadState.refresh is LoadState.Error -> {
                println("pulls.loadState.refresh is LoadState.Error")
            }
        }

        ClosePullsUI(pulls, sdf)
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