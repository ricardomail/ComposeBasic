package com.oasis.composebasic.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.samples.CardElevations
import com.oasis.composebasic.samples.Elevations

@Composable
fun CardSample(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(100.dp)
            .height(100.dp),
        shape = RoundedCornerShape(0, 20, 10, 0),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(1.dp, Color.Blue),
        colors = CardDefaults.cardColors(contentColor = Color.Green, containerColor = Color.Yellow)
    ) {

    }
}

@Preview
@Composable
private fun CardPre() {
    CardSample()
}