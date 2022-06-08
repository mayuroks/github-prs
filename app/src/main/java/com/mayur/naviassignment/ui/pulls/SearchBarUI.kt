package com.mayur.naviassignment.ui.pulls

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import naviassignment.R

@Composable
fun SearchBarUI(
    modifier: Modifier = Modifier,
    viewModel: PullsViewModel,
    onSearchIconClicked: () -> Unit,
) {
    var searchText by viewModel.searchText

    SearchBarUI(
        modifier = modifier,
        searchText = searchText,
        onSearchIconClicked = onSearchIconClicked,
        onSearchTextChange = { searchText = it }
    )
}

@Composable
fun SearchBarUI(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchIconClicked: () -> Unit,
    onSearchTextChange: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {

        Box(
            modifier = Modifier.height(56.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier.background(Color(0xFFF5F5F5)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    placeholder = { Text("owner/repo") },
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    onSearchIconClicked()
                                },
                            painter = painterResource(id = R.drawable.ic_icons8_search),
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarUIPreview() {
    SearchBarUI(
        searchText = "",
        onSearchTextChange = {},
        onSearchIconClicked = {}
    )
}