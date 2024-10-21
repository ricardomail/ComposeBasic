package com.oasis.composebasic.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.R

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun ImagePre() {
    ImageSample()
}


@Composable
fun ImageSample(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(100.dp).background(Color.Blue)) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = null,
            modifier = modifier.size(50.dp),
            contentScale = ContentScale.Crop, // 裁切方式
            colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Color), // 增加滤镜
            alignment = Alignment.TopStart // 图片对其方式
        )
    }

}