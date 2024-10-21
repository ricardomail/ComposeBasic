package com.oasis.composebasic.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun StepPre() {
    StepSample()
}

@Composable
fun StepSample(modifier: Modifier = Modifier) {
//    CircularProgressIndicator(
//        color = Color.Black,
//        progress = {
//            0.5f
//        }
//    )

    LinearProgressIndicator()
}