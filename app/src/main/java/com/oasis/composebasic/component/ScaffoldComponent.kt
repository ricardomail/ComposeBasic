package com.oasis.composebasic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSample(modifier: Modifier = Modifier) {
    Scaffold( topBar = {
        TopAppBar(
            title = { Text(text = "title") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            },
            actions = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = modifier.width(10.dp))
                Text(text = "edit")
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Gray),
        )
    }, bottomBar = {
//        BottomAppBar(actions = {
//            Text(text = "bottom")
//        }, containerColor = Color.Gray)
        NavigationBar(containerColor = Color.Gray) {
            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                },
                label = { Text("like") },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Green,
                    selectedIndicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(selected = false, onClick = {}, icon = {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            })
            NavigationBarItem(selected = false, onClick = {}, icon = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null
                )
            })
        }
    }, ) {
        Text(text = "body", modifier = modifier.padding(it))
    }

}


@Preview
@Composable
private fun ScaffoldPre() {
    ScaffoldSample()
}