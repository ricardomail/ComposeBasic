package com.oasis.composebasic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun SpacerPre() {
    SpacerSample()
}

@Composable
fun SpacerSample(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .width(200.dp)
            .height(300.dp)
            .background(color = Color.Gray),
    ) {

        Text(text = "text1", style = TextStyle(color = Color.White), modifier = Modifier.weight(1f))
        Spacer(modifier = modifier.size(10.dp))
        VerticalDivider(
            thickness = 30.dp,
            color = Color.Yellow
        )
        Text(text = "text2", style = TextStyle(color = Color.White), modifier = Modifier.weight(1f))
    }
}