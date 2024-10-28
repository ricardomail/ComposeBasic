package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties

@Composable
fun DropMenuSample(modifier: Modifier = Modifier) {
    var expand by remember { mutableStateOf(false) }
    Column {
        TextButton(onClick = {
            expand = true
        }) {
            Text("button")
        }

        DropdownMenu(expanded = expand, onDismissRequest = {
            expand = false
        },properties = PopupProperties(focusable = true, dismissOnBackPress = false, dismissOnClickOutside = false)) {
            DropdownMenuItem(onClick = { expand = false }, text = { Text("menu1") })
        }
    }
}

@Preview
@Composable
private fun DropPre() {
    DropMenuSample()
}