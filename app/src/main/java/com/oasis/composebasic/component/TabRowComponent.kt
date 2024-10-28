package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Tab控件中的是Column布局
 * LeadingIcon是Row布局
 */
@Composable
fun TabRowSample(modifier: Modifier = Modifier) {
    var select by remember { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = select,
            indicator = { }, // 下标指示器
            divider = { HorizontalDivider(thickness = 1.dp) }) {
            // 可以使用Tab来实现标签
            Tab(
                selected = (select == 0),
                onClick = { select = 0 },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray,
                modifier = Modifier.height(60.dp)
            ) {
                Text(
                    "Tab1",
                )
            }

            Tab(
                selected = (select == 1),
                onClick = { select = 1 },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            ) {
                Text(
                    "Tab2",
                )
            }

            LeadingIconTab(
                selected = (select == 2),
                onClick = { select = 2 },
                icon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                text = {
                    Text("text2")
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            )

            // 完全自定义
//
//            Text(
//                "Tab2", modifier = modifier
//                    .padding(8.dp)
//                    .clickable {
//                        select = 1
//                    }, style = TextStyle(color = if (select == 1) Color.Yellow else Color.Green)
//            )
//            Text(
//                "Tab3", modifier = modifier
//                    .padding(8.dp)
//                    .clickable {
//                        select = 2
//                    }, style = TextStyle(color = if (select == 2) Color.Yellow else Color.Green)
//            )
        }
    }
}

@Preview
@Composable
private fun TabRowPre() {
    TabRowSample()
}