package com.mayur.naviassignment.components

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mayur.naviassignment.ui.theme.NaviAssignmentTheme


fun ComponentActivity.setContentWithAppTheme(content: @Composable () -> Unit) {
    setContent {
        NaviAssignmentTheme() {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                content()
            }
        }
    }
}
