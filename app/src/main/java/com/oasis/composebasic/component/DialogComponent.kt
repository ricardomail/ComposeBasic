package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogSample(modifier: Modifier = Modifier) {
    var show by remember { mutableStateOf(false) }
    Column {
        Button(onClick = {
            show = true
        }) {
            Text(text = "click me")
        }
        if (show) {
//            Dialog(onDismissRequest = {
//                show = false
//            }) {
//                Surface(modifier = modifier
//                    .width(200.dp)
//                    .height(100.dp), shape = RoundedCornerShape(CornerSize(6.dp))
//                ) {
//                    Text(text = "dialog content", Modifier.align(alignment = Alignment.CenterHorizontally))
//                }
//
//            }

            AlertDialog(
                onDismissRequest = { show = false },
                confirmButton = {
                    TextButton(onClick = { show = false }) {
                        Text(text = "confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { show = false }) {
                        Text(text = "dismiss")
                    }
                },
                text = { Text(text = "this is content") },
                title = { Text(text = "Title") })
        }

    }
}

@Preview
@Composable
private fun DialogPre() {
    DialogSample()
}