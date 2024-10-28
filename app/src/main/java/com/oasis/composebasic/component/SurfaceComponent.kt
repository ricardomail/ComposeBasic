package com.oasis.composebasic.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.R

/**
 * Surface
 * 可以设置形状让子布局裁剪
 * RectangleShape, RoundedCornerShape, CircleShape, CutCornerShape
 * 设置边框，背景
 */
@Composable
fun SurfaceSample(modifier: Modifier = Modifier) {
    Surface(
        shape = CutCornerShape(CornerSize(size = 50.dp)),
        modifier = modifier.background(color = Color.Green).size(300.dp),
        border = BorderStroke(width = 1.dp, color = Color.Red),
        shadowElevation = 10.dp
    ) {
        Image(painter = painterResource(R.drawable.img), contentDescription = null, contentScale = ContentScale.Crop)
    }
}

@Preview
@Composable
private fun SurfacePre() {
    SurfaceSample()
}