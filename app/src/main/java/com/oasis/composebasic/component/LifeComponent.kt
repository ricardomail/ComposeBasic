package com.oasis.composebasic.component

import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.repository.Image
import com.oasis.composebasic.repository.ImageRepository
import com.oasis.composebasic.repository.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * LaunchedEffect
 * 如果在可组合函数中进行耗时操作，就要将耗时操作放入协程中执行
 * 第一次调用compose函数时调用，只执行一次，除非key修改
 * 当LaunchedEffect进入组件树时，会启动一个协程，并将block放入该协程中执行
 * 当组合函数从组件树中detach时，协程还未执行完毕，该协程也会被取消执行
 * 当LaunchedEffect在重组的时候其key不变，那LaunchedEffect不会被重新启动执行block
 * 当LaunchedEffect在重组时其key发生了改变，则LaunchedEffect会执行cancel后再重新启动一个新的协程执行block
 *
 *
 * rememberCoroutineScope
 * LaunchedEffect是可组合函数，因此只能在其他可组合函数中使用，想要在可组合项外启动协程，且需要对这个协程存在的作用域限制
 * 以便协程退出组合后自动取消，使用rememberCoroutineScope，并且，如果需要手动控制一个或多个协程的生命周期，也使用rememberCoroutineScope
 *
 *
 * rememberUpdatedState
 * 给某个参数创建一个引用，来跟踪这些参数，并保证其值被使用的时候是最新值，参数被改变时不重启Effect
 * 配合LaunchedEffect来使用，可以模拟首页加载横竖屏切换
 *
 *
 * DisposableEffect
 * 可组合函数，当其Key发生变化或者组合函数离开组件树时，会取消之前启动的协程，并会在取消协程前调用其回收方法进行资源回收相关操作
 * 可以对一些资源进行清理
 *
 *
 * SideEffect
 * 简化版的DisposableEffect，并未接收任何key值，每次重组，都会执行其block，当不需要onDispose，不需要参数控制时使用。
 * 主要用来于非Compose管理的对象共享Compose状态
 * SideEffect在组合函数被创建并载入视图树后才会被调用
 *
 * produceState
 * 可以将非Compose（Flow，LiveData，Rxjava）状态转换为Compose状态，它接收一个lambda表达式作为函数体，能将
 * 这些入参经过一些操作后生成一个State类型变量并返回
 * produceState创建了一个协程，但它也可以用于观察非挂起的数据源。
 * 当ProduceState进入Composition时，获取数据的任务被启动，当其离开时，该任务被取消
 * 当key发生改变时，会重新执行ProduceState中的代码
 *
 */

@Composable
fun LifecycleSample(modifier: Modifier = Modifier) {
//    val state = remember { mutableStateOf(false) }
//    LaunchEffectSample(state = state)
    RememberCor()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberCor(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        Column(
            modifier = modifier
                .background(color = Color.Gray)
                .width(300.dp)
                .fillMaxHeight()
        ) {
            Text("HHHH")
        }
    }, content = {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(title = { Text("Title") }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                    }
                })
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                    }) {
                        Text("Error occurs")
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("hello")
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            },
        )
    })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchEffectSample(modifier: Modifier = Modifier, state: MutableState<Boolean>) {
    val snackbarHostState = remember { SnackbarHostState() }
    if (state.value) {
        LaunchedEffect(key1 = state.value) {
            snackbarHostState.showSnackbar(message = "Error message", actionLabel = "Retry message")
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopAppBar(title = { Text("Title") }) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    state.value = !state.value
                }) {
                    Text("Error occurs")
                }
            }
        })
}

@Preview
@Composable
private fun LaunchPre() {
    ProduceStateSample()
}

@Composable
fun BackHandler(backDispatcher: OnBackPressedDispatcher, onBack: () -> Unit) {
    val onBackState by rememberUpdatedState(onBack)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackState()
            }
        }
    }
    DisposableEffect(backDispatcher) {
        backDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
            Log.i("====", "BackHandler: onDispose")
        }
    }
}


@Composable
fun DisposableEffectSample(
    modifier: Modifier = Modifier.background(color = Color.Gray),
    backDispatcher: OnBackPressedDispatcher
) {
    var switch by remember { mutableStateOf(false) }
    Row {
        Switch(checked = switch, onCheckedChange = {
            switch = it
        })
    }
    if (switch) {
        BackHandler(backDispatcher) {
            Log.i("====", "DisposableEffectSample: ")
        }
    }


}


@Composable
fun Example() {
    val clicked = remember { mutableStateOf(0) }
    Log.d("====", "side effect before: ${clicked.value}")
    DisposableEffect(Unit) {
        Log.d("====", "side effect called")
        onDispose { }
    }
    Log.d("====", "side effect after: ${clicked.value}")
    Column {
        Text(
            text = "Clicked: ${clicked.value}", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    clicked.value = clicked.value + 1
                }
        )
    }
    Log.d("====", "bottom effect: ${clicked.value}")

}

@Composable
fun loadNetWordImage(
    modifier: Modifier = Modifier,
    url: String,
): State<Results<Image>> {
    val context = LocalContext.current
    val imageRepository = remember {  ImageRepository(context) }
    val produceState =
        produceState(initialValue = Results.Loading as Results<Image>, url) {
            value = Results.Loading
            Log.i("===", "${Thread.currentThread().name}: ")
            val image = imageRepository.load(url)
            value = if (image == null) Results.Error else Results.Success(image)
        }
    Log.i("===", "return state: $produceState")
    return produceState

}


@Composable
fun ProduceStateSample(modifier: Modifier = Modifier) {
    val imageList = listOf(
        "https://img-blog.csdnimg.cn/2021051521244130.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl81MzQ0Nzc3Ng==,size_16,color_FFFFFF,t_70",
        "https://www.bing.com/images/search?view=detailV2&ccid=nkWmM%2blR&id=87D6C0015B81510AF7846479089CEE844B8FCB68&thid=OIP.nkWmM-lReaN8kH-ieXmZrQHaEo&med3dhttp%253a%252f%252fwww.quazero.com%252fuploads%252fallimg%252f140303%252f1-140303214Q2.jpg%26ehk%3dP%252firfYpARc1fHht%252bWpapYR4W15p6SLABE8CBexoeon4%253d%26risl%3d%26pid%3dImgRaw%26r%3d0&exph=1200&expw=1920&q=%e5%9b%be%e7%89%87&simid=607987569811938027&FORM=IRPRST&ck=9117900F9004E980425AC2F8E09D1535&selectedIndex=8&itb=0",
        "https://img.zcool.cn/community/017f51563447666ac7259e0f1522ea.jpg@1280w_1l_2o_100sh.jpg"
    )
    Log.i("===", "init")
    var index by remember { mutableIntStateOf(0) }
    val result = loadNetWordImage(url = imageList[index])

    Column {
        Button(onClick = {
            index %= imageList.size
            if (++index == imageList.size) index = 0
        }) {
            Text(text = "当前选择第${index}图片")
        }
        Log.i("===", "ProduceStateSample: ${result.value}")
        when (result.value) {
            is Results.Success -> {
                Image(
                    bitmap = (result.value as Results.Success).image!!.imageBitmap,
                    contentDescription = null
                )
            }

            is Results.Error -> {
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null,
                    modifier = modifier.size(200.dp, 200.dp)
                )
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}


