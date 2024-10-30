package com.oasis.composebasic.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.R

@Preview
@Composable
private fun Animation1() {
    Animation1Sample()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Animation1Sample(modifier: Modifier = Modifier) {
    var visibility = remember { MutableTransitionState(false).apply { targetState = true }}
    Column {
        Button(onClick = {
            visibility.targetState = !visibility.targetState
        }) {
            Text(text = "click")
        }

        AnimatedVisibility(
            visibleState = visibility,
            enter = EnterTransition.None,
            exit = ExitTransition.None
//            enter = scaleIn() + expandVertically(),
//            exit = scaleOut() + shrinkVertically()
        ) {
//            transition.animateColor {  }

            Image(painter = painterResource(R.drawable.img), contentDescription = null)
            Box(modifier = modifier.animateEnterExit(
                exit = slideOutVertically(animationSpec = tween(2000)),
                enter = slideInVertically()
            ).size(100.dp).background(color = Color.Green)) {  }
        }
    }
}