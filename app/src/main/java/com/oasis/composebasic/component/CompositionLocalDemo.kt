package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * 使用compositionLocal隐式传递参数
 */
val localActiveUser = compositionLocalOf<User> { error("user is null") }

data class User(val name: String)

@Preview
@Composable
private fun CompPre() {
    Composition()
}

@Composable
fun Composition(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val user = User("Test")
    CompositionLocalProvider(localActiveUser provides user) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home"){
                HomeScreen {
                    navController.navigate("Detail")
                }
            }
            composable("Detail"){
                DetailScreen()
            }
        }
    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onTap: () -> Unit) {
    Column {
        Text(text = "HomeScreen")
        Button(onClick = onTap) {
            Text(text = "Navigation to Detail")
        }
    }
}

@Composable
fun DetailScreen(modifier: Modifier = Modifier, ) {
    Column {
        Text(text = "DetailScreen ${localActiveUser.current.name}")
    }
}