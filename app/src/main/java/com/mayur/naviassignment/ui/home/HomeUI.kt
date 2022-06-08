package com.mayur.naviassignment.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.mayur.naviassignment.components.ErrorUI
import com.mayur.naviassignment.components.showShortToast
import com.mayur.naviassignment.ui.pulls.ClosedPRListUI
import com.mayur.naviassignment.ui.pulls.PagingState
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import com.mayur.naviassignment.ui.pulls.SearchBarUI
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat

// Show API error msg

@Composable
fun HomeUI(
    viewModel: PullsViewModel,
    sdf: SimpleDateFormat,
    onSearchIconClicked: () -> Unit,
) {
    val pagingState by viewModel.pagingState
    val pullsFlow by viewModel.pulls

    val pulls = pullsFlow?.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewModel.getClosedPRs()
    }

    Column {
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
        )

        SearchBarUI(
            modifier = Modifier.padding(
                top = 30.dp,
                bottom = 12.dp,
                start = 12.dp,
                end = 12.dp
            ),
            viewModel = viewModel,
            onSearchIconClicked = onSearchIconClicked,
        )

        when (pagingState) {
            is PagingState.AppendError ->
                showShortToast("More items cant be added")
            is PagingState.RefreshError ->
                ErrorUI {
                    viewModel.getClosedPRs()
                }
            is PagingState.Loading -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth()
                    )

                    if ((pulls?.itemCount ?: 0) > 0) {
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "Closed PRs:"
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                            .fillMaxWidth()
                    )
                    ClosedPRListUI(
                        viewModel = viewModel,
                        pulls = pulls,
                        sdf = sdf
                    )
                }
            }
            is PagingState.RetryLoading -> {
                viewModel.handlePagingLoading()
                LaunchedEffect(key1 = Unit) {
                    delay(100)
                    pulls?.retry()
                }
            }
        }
    }
}
