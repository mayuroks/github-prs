package com.mayur.naviassignment.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mayur.naviassignment.ui.pulls.ClosedPRsUI
import com.mayur.naviassignment.ui.pulls.PullsViewModel
import java.text.SimpleDateFormat

@Composable
fun HomeUI(viewModel: PullsViewModel, sdf: SimpleDateFormat) {
    val closedPRRequest by viewModel.closedPRRequest

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth())
        Text(text = "Repo: ${closedPRRequest.owner}/${closedPRRequest.repo}")
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text(text = "Showing Closed PRs:")
        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth())
        ClosedPRsUI(
            viewModel = viewModel,
            sdf = sdf
        )
    }
}
