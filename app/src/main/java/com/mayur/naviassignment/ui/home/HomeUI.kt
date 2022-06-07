package com.mayur.naviassignment.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mayur.naviassignment.components.ErrorUI
import com.mayur.naviassignment.components.showShortToast
import com.mayur.naviassignment.data.pulls.ClosedPRRequest
import com.mayur.naviassignment.ui.pulls.ClosedPRListUI
import com.mayur.naviassignment.ui.pulls.PagingState
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import java.text.SimpleDateFormat

@Composable
fun HomeUI(viewModel: PullsViewModel, sdf: SimpleDateFormat) {
    val closedPRRequest by viewModel.closedPRRequest
    val pagingState by viewModel.pagingState

    LaunchedEffect(key1 = Unit) {
        viewModel.getClosedPRs(ClosedPRRequest.getInitialRepo())
    }

    when (pagingState) {
        is PagingState.AppendError ->
            showShortToast("More items cant be added")
        is PagingState.RefreshError ->
            ErrorUI {
                viewModel.getClosedPRs(ClosedPRRequest.getInitialRepo())
            }
        is PagingState.Loading -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(4.dp).fillMaxWidth())
                Text(text = "Repo: ${closedPRRequest.owner}/${closedPRRequest.repo}")
                Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
                Text(text = "Showing Closed PRs:")
                Spacer(modifier = Modifier.height(4.dp).fillMaxWidth())
                ClosedPRListUI(
                    viewModel = viewModel,
                    sdf = sdf
                )
            }
        }
    }
}
