package com.oasis.composebasic.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
private fun NavPre() {
    NavSample()
}

@Composable
fun NavSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "First") {
        composable(route = "First") {
            FirstScreen(nav = navController)
        }
        composable(route = "Second") {
            SecondScreen(nav = navController)
        }
        composable(route = "Third") {
            ThirdScreen(nav = navController)
        }

    }
}

@Composable
fun FirstScreen(modifier: Modifier = Modifier, nav: NavController) {
    Column {
        Text(text = "First Screen")

        Button(onClick = {
            nav.navigate("Second")
        }) {
            Text(text = "Navigate To Second Screen")
        }
    }
}

@Composable
fun SecondScreen(modifier: Modifier = Modifier, nav: NavController) {
    Column {
        Text(text = "Second Screen")

        Button(onClick = {
            nav.navigate("Third"){
                launchSingleTop = true
            }
        }) {
            Text(text = "Navigate To Third Screen")
        }
    }
}


@Composable
fun ThirdScreen(modifier: Modifier = Modifier, nav: NavController) {
    Column {
        Text(text = "Third Screen")

        Button(onClick = {
            nav.navigate("First")
        }) {
            Text(text = "Navigate To First Screen")
        }
    }
}
