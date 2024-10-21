package com.oasis.composebasic.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

/**
 * outlineTextField BasicTextField TextField
 */
@Composable
fun TextFieldSample(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            // 文本改变的回调方法
            text = it
        },
        label = {
            // 添加标签
            Text("name")
        },
        placeholder = {
            Text("input names")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Call, contentDescription = null)
        },
        keyboardActions = KeyboardActions(onDone = {

        }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ), singleLine = true
    )
}

@Preview
@Composable
private fun TextFieldPre() {
    TextFieldSample()
}