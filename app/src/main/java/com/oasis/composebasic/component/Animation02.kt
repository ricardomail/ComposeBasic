package com.oasis.composebasic.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
private fun Animation2() {
    Animation1Sample2()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Animation1Sample2(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Row {
        Button(onClick = {
            count++
        }) {
            Text("add")
        }
        Column {
            AnimatedContent(targetState = count) { targetState ->
                Text("count: $targetState")
            }

            AnimatedContent(targetState = count, transitionSpec = {
                val contentTransform = slideInVertically { fullHeight ->
                    fullHeight
                } + fadeIn() togetherWith slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
                contentTransform using SizeTransform(clip = true)

            }) { targetState ->
                Text("count: $targetState")
            }
        }

    }
}