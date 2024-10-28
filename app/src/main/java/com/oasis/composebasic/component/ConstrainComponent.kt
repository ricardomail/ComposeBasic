package com.oasis.composebasic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintSample(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Yellow)
    ) {
        val (icon, primary, secondary, checkbox) = createRefs() // 创建约束关联名称

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = modifier
                .constrainAs(icon) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start, margin = 10.dp)
                }
                .size(40.dp)
        )
        Text(
            text = "primary Text",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 26.sp),
            modifier = modifier.constrainAs(primary) {
                start.linkTo(icon.end, margin = 10.dp, goneMargin = 10.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(
            text = "secondary Text",
            fontWeight = FontWeight.Normal,
            style = TextStyle(fontSize = 12.sp),
            modifier = modifier.constrainAs(secondary) {
                start.linkTo(primary.start)
                top.linkTo(primary.bottom, margin = (-13).dp)
                bottom.linkTo(parent.bottom)
            }
        )
        Checkbox(checked = checked, onCheckedChange = {
            checked = it
        }, modifier = modifier.constrainAs(checkbox) {
            centerVerticallyTo(parent)
            end.linkTo(parent.end, margin = 10.dp)
        })
    }
}

@Preview
@Composable
private fun ConstrainPre() {
    ConstraintSample()
}