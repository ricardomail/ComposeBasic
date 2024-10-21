package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.oasis.composebasic.R

/**
 * 如果使用官方图标，默认比较少，需要手动添加图标的扩展库，然后才能使用
 *
 */
@Composable
fun IconSample(modifier: Modifier = Modifier) {
    Column {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            tint = Color.Blue // 设置图标颜色
        ) // 使用官方提供的icon
//        Icon(imageVector = Icons.Default.Tra)
        Icon(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            tint = Color.Green
        )
    }
}

@Preview
@Composable
private fun IconPre() {
    IconSample()
}