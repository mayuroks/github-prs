package com.mayur.naviassignment.components

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun showShortToast(msg: String) {
    Toast.makeText(LocalContext.current, msg, Toast.LENGTH_SHORT).show()
}
