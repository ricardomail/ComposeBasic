package com.oasis.composebasic.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun SlidePre() {
    SlideSample()
}

@Composable
fun SlideSample(modifier: Modifier = Modifier) {

//    var value by remember { mutableFloatStateOf(0f) }
//    Slider(value = value, onValueChange = {
//        value = it
//    }, valueRange = 0f..100f, steps = 4)

    // 范围选择器
    var values by remember { mutableStateOf(0.2f..0.5f) }
    RangeSlider(value = values, onValueChange = {
        values = it
    })
}