package com.oasis.composebasic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(10.dp) // 这两个式有前后顺序的区别的
            .clickable { }
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f) // 颜色rgp正常使用，a自定义
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            Text(
                text = "3 minutes ago",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutStudy() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("LayoutStudy")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = null)
                    }
                }
            )
        }) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "123187128937")
        Text(text = "123187128937adfadfadfafadsf")
    }
}

// 默认无法滚动
@Composable
fun SimpleColumn() {
    val scrollState = rememberScrollState() // 添加后可以滚动
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text(text = "item $it", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun LazyList(size: Int, state: LazyListState) {

    LazyColumn(state = state) {
        items(size) {
            Text(text = "item $it", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Column {
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                scope.launch { scrollState.animateScrollToItem(0) }
            }) {
                Text("top")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                scope.launch { scrollState.animateScrollToItem(listSize - 1) }
            }) {
                Text("end")
            }
        }
        LazyList(listSize, state = scrollState)
    }

}

fun Modifier.firstBaselineToTop(firstBaseLineToTop: Dp): Modifier =
    this.then(layout { measurable, constraints ->
        // 测量元素
        val placeable = measurable.measure(constraints)
        val baseline = placeable[FirstBaseline]
        val y = firstBaseLineToTop.roundToPx() - baseline
        layout(placeable.width, placeable.height + y) {
            placeable.placeRelative(0, y)
        }
    })


@Composable
fun TextWithPaddingToBaseline() {
    Text(
        text = "Hi there", modifier = Modifier
//            .padding(24.dp)
            .firstBaselineToTop(24.dp)
            .background(Color.Blue)
    )
}

@Composable
fun MyOwnColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measureables, constrains ->
        val placeables = measureables.map {
            it.measure(constrains)
        }
        var yPosition = 0
        // 布局大小
        layout(constrains.maxWidth, constrains.maxHeight) {
            placeables.forEach {
                it.placeRelative(x = 0, y = yPosition)
                yPosition += it.height
            }
        }
    }
}

/**
 * 约束布局
 */
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text) = createRefs() // 创建引用
        // 关联引用
        Button(onClick = {}, modifier = Modifier.constrainAs(button){
            top.linkTo(parent.top, margin = 16.dp) // 使用约束
        }) {
            Text("button")
        }

        Text("Hello", modifier = Modifier.constrainAs(text){
            top.linkTo(button.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        })
    }
}

@Composable
fun ConstraintLayoutContent3() {
    ConstraintLayout {
        val text = createRef() // 创建引用
        val guideline = createGuidelineFromStart(fraction = 0.5f) // 引导线
        Text(
            text = "lakjdflkajdlkfjaldfjalsdjflajdlfjalkdjflkajdslfjalskdjflkaf",
            modifier = Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}
