package com.oasis.composebasic.component

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun GestureSample(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        TransformableSample()
    }
}

@Composable
fun ClickableSample() {
    var count by remember { mutableIntStateOf(0) }
    Text(
        text = "$count",
        modifier = Modifier
            .pointerInput(Unit) { //点击
                detectTapGestures(
                    onPress = {},
                    onDoubleTap = { count++ },
                    onLongPress = {},
                    onTap = {})
            }
//            .clickable {
//                count++
//            }
            .wrapContentSize()
            .background(Color.Gray)
            .padding(horizontal = 50.dp, vertical = 10.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun ScrollBoxes(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(enabled = true, state = scrollState)
    ) {
        repeat(10) {
            Text(text = "item = ${it}", modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
fun ScrollBoxesSmooth(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        scrollState.animateScrollTo(400, animationSpec = TweenSpec(durationMillis = 2000))
    }
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(enabled = true, state = scrollState)
    ) {
        repeat(10) {
            Text(text = "item = ${it}", modifier = Modifier.padding(20.dp))
        }
    }
}


@Preview
@Composable
private fun GesturePre() {
    GestureSample()
}


@Composable
fun ScrollableSample(modifier: Modifier = Modifier) {
    var offset by remember { mutableStateOf(0f) }
    Box(
        modifier = modifier
            .size(150.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$offset")
    }
}

/**
 * 嵌套滑动
 */
@Composable
fun NestedScrollSample(modifier: Modifier = Modifier) {
    val gradient = Brush.verticalGradient(0f to Color.Yellow, 1000f to Color.Gray) // 渐变色
    Box(
        modifier = modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column {
            repeat(6) {
                Box(
                    modifier = modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(gradient)
                            .padding(24.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }
}

/**
 * 拖动
 */
@Composable
fun DragSample(modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) }
    Text(
        text = "drag me",
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal, // 允许水平拖动
                state = rememberDraggableState {
                    offsetX += it
                })
    )
}

/**
 * 随意拖动
 */
@Composable
fun DragSample2(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        var offsetX by remember { mutableFloatStateOf(0f) }
        var offsetY by remember { mutableFloatStateOf(0f) }
        Box(modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(color = Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            })
    }
}

//@Composable
//fun SwipeSample(modifier: Modifier = Modifier) {
//    val swipeableState = rememberSwipeableState(0)
//    val sizePx = with(LocalDensity.current) {
//        (100.dp).toPx()
//    }
//    val anchors = mapOf(0f to 0, sizePx to 1)
//    Box(
//        modifier = modifier
//            .width(200.dp)
//            .height(100.dp)
//            .background(color = Color.Gray)
//            .swipeable(
//                state = swipeableState, anchors = anchors, thresholds = { _, _ ->
//                    FractionalThreshold(0.3f) // 阈值
//                }, orientation = Orientation.Horizontal
//            )
//    ) {
//        Box(
//            modifier = Modifier
//                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
//                .width(100.dp)
//                .height(100.dp)
//                .background(color = Color.Red)
//        )
//    }
//}


@Composable
fun DraggableSwipeableSample() {
    // Define the anchors for snapping behavior
    val sizePx = with(LocalDensity.current) {
        (100.dp).toPx()
    }
    val parentWidthPx = with(LocalDensity.current) {
        (200.dp).toPx()
    }
    val maxOffset = parentWidthPx - sizePx
    val anchors = mapOf(0f to 0, sizePx to 1)
    // Define the state for the draggable offset

    // Define the offset as an Animatable
    var offset by remember { mutableFloatStateOf(0f) }
    val draggableState = rememberDraggableState { delta ->
        val newOffset = (offset + delta).coerceIn(0f, maxOffset)
        if (newOffset != offset) {
            offset = newOffset
        }

    }
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
            .background(color = Color.LightGray)
            .draggable(
                state = draggableState,
                orientation = Orientation.Horizontal,
                onDragStopped = {
                    // Find the closest anchor and animate to it
                    val target = anchors.entries.minByOrNull { (anchor, _) ->
                        kotlin.math.abs(anchor - offset)
                    }?.key ?: 0f
                    offset = target
                }
            )
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offset.roundToInt(), 0) }
                .width(100.dp)
                .height(100.dp)
                .background(color = Color.Red)
        )
    }
}

/**
 * 多点触控
 */
@Composable
fun TransformableSample(modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += panChange
    }
    Box(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state)
            .background(color = Color.Blue)
            .size(100.dp)
    )
}