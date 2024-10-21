package com.oasis.composebasic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * modifiers 用来修饰一个composable
 * 可以改变composable的大小，布局，动作和外观
 * 添加信息，比如无障碍辅助信息
 * 处理用户输入
 * 增加高级交互，比如点击，滚动，拖动或缩放等
 */
@Composable
fun ModifierSample(modifier: Modifier = Modifier) {
    Text(
        text = "chinese people",
//        style = TextStyle(background = Color.Green),
        modifier = modifier
            // 如果先设置padding，后设置背景颜色会出现无效的情况，设置顺序很重要
            .background(color = Color.Gray) // 设置背景
            .border(1.dp, color = Color.Blue)
            .padding(20.dp)
            .clickable {
                // 点击回调
            }.fillMaxWidth().fillMaxHeight()

    )
}


@Preview
@Composable
private fun prevDefault() {
    ModifierSample()
}