package com.oasis.composebasic.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(widthDp = 200)
@Composable
private fun BoxPre() {
    BoxSample()
}


@Composable
fun BoxSample(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//    ) {
//        Box(modifier = modifier
//            .size(100.dp)
//            .background(color = Color.Blue).align(Alignment.Center))
//        Box(modifier = modifier
//            .size(50.dp)
//            .background(color = Color.Green).align(Alignment.BottomCenter))
//    }

    BoxWithConstraints() {
        val maxWidth = constraints.maxWidth
        val maxHeight = constraints.maxHeight

        Box(modifier = modifier
            .size(100.dp)
            .background(color = Color.Blue).align(Alignment.Center))
        Box(modifier = modifier
            .size(50.dp)
            .background(color = Color.Green).align(Alignment.BottomCenter))
    }
}