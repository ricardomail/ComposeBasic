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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

/**
 * 将constraint约束提取出来
 */
@Composable
fun ConstraintSample2(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Yellow), constraintSet = createConstrain()
    ) {

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = modifier
                .layoutId("icon")
                .size(40.dp)
        )
        Text(
            text = "primary Text",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 26.sp),
            modifier = modifier.layoutId("primary")
        )
        Text(
            text = "secondary Text",
            fontWeight = FontWeight.Normal,
            style = TextStyle(fontSize = 12.sp),
            modifier = modifier.layoutId("secondary")
        )
        Checkbox(checked = checked, onCheckedChange = {
            checked = it
        }, modifier = modifier.layoutId("checkbox"))
    }
}

fun createConstrain(): ConstraintSet {
    return ConstraintSet {
        val icon = createRefFor("icon")
        val primary = createRefFor("primary")
        val secondary = createRefFor("secondary")
        val checkbox = createRefFor("checkbox")

        constrain(icon) {
            centerVerticallyTo(parent)
            start.linkTo(parent.start, margin = 10.dp)
        }
        constrain(primary) {
            centerVerticallyTo(parent)
            start.linkTo(icon.end, margin = 10.dp)
        }
        constrain(secondary) {
            top.linkTo(primary.bottom, margin = 5.dp)
            start.linkTo(primary.start)
        }
        constrain(checkbox) {
            end.linkTo(parent.end)
            centerVerticallyTo(parent)
        }
    }
}

@Preview
@Composable
private fun ConstrainPre2() {
    ConstraintSample2()
}