package com.oasis.composebasic.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.R

@Preview
@Composable
private fun CanvasPre() {
    CanvasSample()
}

@Composable
fun CanvasSample() {

    val image = ImageBitmap.imageResource(R.drawable.img).apply {  }
    Canvas(modifier = Modifier.size(200.dp)) {

        drawLine(
            color = Color.Blue,
            start = Offset(0f, 0f),
            end = Offset(100f, 200f),
            strokeWidth = 30f,
            cap = StrokeCap.Butt
        )
    }
}

