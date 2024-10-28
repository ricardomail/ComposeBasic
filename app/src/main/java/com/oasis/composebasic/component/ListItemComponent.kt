package com.oasis.composebasic.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.log

data class Item(val title: String, var checked: Boolean = false)

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ListItemSample(modifier: Modifier = Modifier) {
    var list by remember {
        mutableStateOf(
            mutableListOf(
                Item("item1"),
                Item("item2"),
                Item("item3"),
                Item("item4"),
                Item("item5"),
                Item("item6"),
                Item("item7"),
                Item("item8"),
                Item("item9")
            )
        )
    }
    val rememberCoroutineScope = rememberCoroutineScope()
    val state = rememberScrollState() // 滑动
    Log.d("=====", "start: ")
    Column(modifier = modifier.verticalScroll(state)) {
        list.forEach { outer ->
            ListItem(headlineContent = {
                Text(text = outer.title)
            }, supportingContent = {
                Text(text = "supportingContent")
            }, leadingContent = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = modifier
                        .width(40.dp)
                        .height(40.dp)
                )
            }, trailingContent = {
                Log.d("===", "ListItemSample: ${outer.checked}")
                Checkbox(checked = outer.checked, onCheckedChange = { checked ->
                    list = list.map { item ->
                        if (item.title == outer.title) item.copy(checked = checked)
                        else item
                    }.toMutableList()
                })
            }, overlineContent = {
                Text(text = "title", modifier = Modifier.clickable {
                    rememberCoroutineScope.launch { state.scrollTo(state.maxValue) }
                })
            })
        }
    }
}

@Preview
@Composable
private fun ListItemPre() {
    ListItemSample()
}