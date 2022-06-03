package com.mayur.naviassignment.closedpulls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mayur.naviassignment.data.pulls.PullRequest
import com.mayur.naviassignment.data.pulls.User
import naviassignment.R
import java.text.SimpleDateFormat
import java.util.*

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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 6.dp)
            .clip(RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.background(Color(0xFFF5F5F5))) {
            Row(modifier = Modifier.padding(12.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp),
                    model = pull.user.avatarUrl,
                    placeholder = painterResource(R.drawable.kf_pan),
                    contentDescription = stringResource(R.string.app_name)
                )

                Spacer(modifier = Modifier.padding(6.dp))

                Column {
                    Text(text = pull.title + " #" + pull.number)
                    Text(text = "Created: " + sdf.format(pull.createdAt))
                    Text(text = "Closed/Merged: " + sdf.format(pull.closedAt ?: ""))
                    Text(text = "By user: " + pull.user.username)
                }
            }
        }
    }
}

//@Preview
@Composable
fun ClosedPullUIPreview() {
    val pullRequest = PullRequest(
        id = 12121,
        title = "IR] RFC: Have JVM codegen respect the GenerateClassFilter supplied to GenerationState",
        number = 38920,
        User("aeawewe", username = "kandersen"),
        createdAt = Date(),
        closedAt = Date()
    )
    val sdf = SimpleDateFormat("d MMMM yy")

    ClosePullUI(
        pull = pullRequest,
        sdf = sdf
    )
}

@Preview
@Composable
fun ClosedPullsUIPreview() {
    val pullRequest = PullRequest(
        id = 12121,
        title = "IR] RFC: Have JVM codegen respect the GenerateClassFilter supplied to GenerationState",
        number = 38920,
        User("aeawewe", username = "kandersen"),
        createdAt = Date(),
        closedAt = Date()
    )
    val sdf = SimpleDateFormat("d MMMM yy")

    val pulls = listOf(pullRequest, pullRequest, pullRequest)

    ClosePullsUI(pulls = pulls, sdf = sdf)
}
