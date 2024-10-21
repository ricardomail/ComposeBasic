package com.oasis.composebasic.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * state用于状态的更新，当state值发生改变的时候，composable会发生重组，进行UI刷新，但由于重新执行了对应的composable
 * 方法，所以需要使用remember来记录数据，在发生重组的时候返回记录的值
 *
 * 其他可观察对象，在compose使用这些之前，需要将其转换为State再使用
 * LiveData
 * Flow
 * RxJava2
 *
 * 状态提升：有助于子控件的复用
 * 状态恢复：rememberSaveAble
 *
 * 下面记录了三种写法来使用state和remember
 */
@Composable
fun StateSample(modifier: Modifier = Modifier) {
    Log.i("=====", "StateSample: ")
//    var count by remember {  mutableIntStateOf(1) }
//    val count = remember { mutableIntStateOf(1) }
    val (count, setCount) = remember { mutableIntStateOf(1) }
    Text(text = "current value = $count", modifier = modifier.clickable {
        setCount(count + 1)
    })
}

@Preview
@Composable
private fun StatePre() {
    StateSample()
}