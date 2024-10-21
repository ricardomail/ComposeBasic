package com.oasis.composebasic.component

import android.util.Log
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun SwitchPre() {
    SwitchSample()
}

@Composable
fun SwitchSample(modifier: Modifier = Modifier) {
    var check by remember { mutableStateOf(false) }
    Switch(checked = check, onCheckedChange = {
        Log.i("====", "SwitchSample: $it")
        check = it
    })
}