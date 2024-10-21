package com.oasis.composebasic.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * button扩展还有
 * TextButton outlineButton IconButton
 */
@Composable
fun ButtonSample(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            Log.d("====", "ButtonSample: ")
        },
        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp),
//        colors = ButtonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black,
//            disabledContentColor = Color.Black,
//            disabledContainerColor = Color.White
//        ) // 设置按钮颜色
        colors = ButtonDefaults.buttonColors()
            .copy(contentColor = Color.Black, containerColor = Color.White) // 通过默认值的方式设置颜色

    ) {
        Text("button")
    }
}

@Preview
@Composable
private fun ButtonPre() {
    ButtonSample()
}