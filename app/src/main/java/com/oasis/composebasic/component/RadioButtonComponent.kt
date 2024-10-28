package com.oasis.composebasic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun RadioButtonPre() {
    RadioButtonSample()
}

@Composable
fun RadioButtonSample(modifier: Modifier = Modifier) {
    // 单个选择器
//    var select by remember { mutableStateOf(false) }
//    RadioButton(selected = select, onClick = {
//        select = !select
//    })

    // 多个选择器互斥
    var checkedList by remember { mutableStateOf(mutableListOf(false, false)) }
    Column {
        checkedList.forEachIndexed { index, item ->

            RadioButton(selected = item, onClick = {
                checkedList = List(checkedList.size) { i -> index == i }.toMutableList()
            })
        }

    }

    var check by remember { mutableStateOf(false) }
    Checkbox(checked = check, onCheckedChange = {checked ->
        check = checked
    })


}