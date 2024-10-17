package com.oasis.composebasic.todo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TodoItemInlineEditor(
    item: TodoItem,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    TodoItemInput(text = item.task,
        onTextChange = { onEditItemChange(item.copy(task = it)) },
        icon = item.icon,
        onIconChange = { onEditItemChange(item.copy(icon = it)) },
        submit = onEditDone,
        iconsVisible = true,
        buttonSlot = {
            Row(Modifier.fillMaxWidth().background(color = Color.Gray), horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = {onRemoveItem(item)}, Modifier.background(color = Color.Green).widthIn(max = 30.dp).padding(0.dp)) {
                    Text(
                        text = "X",
                        textAlign = TextAlign.Center
                    )
                }

                TextButton(onClick = onEditDone,Modifier.background(color = Color.Blue).padding(0.dp)) {
                    Text(
                        text = "\uD83D\uDCBE",
                        textAlign = TextAlign.Center


                    )
                }

            }
        })
}

// 顶部输入框增加灰色背景
@Composable
fun TodoItemInputBackground(
    modifier: Modifier = Modifier,
    elevate: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    // 帧动画的形式展现Surface底部阴影
    val animatedElevation by animateDpAsState(
        if (elevate) 1.dp else 0.dp, TweenSpec(100),
        label = ""
    )
    Surface(
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
        shape = RectangleShape,
        shadowElevation = animatedElevation,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
            content = content
        )
    }
}

@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    Surface {
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = modifier,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            // 处理软键盘
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction()
                keyboardController?.hide()
            })
        )
    }
}

@Composable
fun TodoEditButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true
) {

    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enable,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = text)
    }
}


// 下排图标
@Composable
fun AnimatedIconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true
) {
    val enter = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) }
    val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }

    Box(modifier = modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(visible = visible, enter = enter, exit = exit) {
            IconRow(icon = icon, onIconChange = onIconChange)
        }
    }
}

@Composable
fun IconRow(icon: TodoIcon, onIconChange: (TodoIcon) -> Unit, modifier: Modifier = Modifier) {

    Row(modifier = modifier) {
        for (todoIcon in TodoIcon.entries) {
            SelectableIconButton(
                icon = todoIcon.imageVector,
                iconContentDescription = todoIcon.contentDescription,
                onIconSelected = { onIconChange(todoIcon) },
                isSelected = todoIcon == icon
            )
        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    iconContentDescription: String,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    // 图标颜色
    val tint =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.6f
        )

    TextButton(onClick = { onIconSelected() }, shape = CircleShape, modifier = modifier) {
        Column {
            Icon(imageVector = icon, contentDescription = iconContentDescription, tint = tint)
            if (isSelected) {
                Box(
                    Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else {
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun TodoItemEntryInput(onItemComplete: (TodoItem) -> Unit) {

    // 函数使用remember给自己添加内存，然后在内存中存储一个由mutableStateOf创建的MutableState<String>
    // 它是Compose的内置类型，提供了一个可观察的状态持有者
    // 对value的任何更改豆浆自动重组组合读取此状态的任何可组合函数(比如TextButton中的isNotEmpty)
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }

    val iconsVisible = text.isNotBlank()

    val submit = {
        onItemComplete(TodoItem(text, icon = icon))
        setIcon(TodoIcon.Default)
        setText("")
    }
    TodoItemInput(
        text = text,
        onTextChange = setText,
        onIconChange = setIcon,
        iconsVisible = iconsVisible,
        submit = submit,
        icon = icon,
        buttonSlot = {
            TodoEditButton(
                text = "add",
                onClick = submit,
                enable = text.isNotBlank()
            )
        }
    )
}


@Composable
fun TodoItemInput(
    text: String, onTextChange: (String) -> Unit,
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean,
    buttonSlot: @Composable () -> Unit

) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit

            )

            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(Alignment.CenterVertically)) { buttonSlot() }
        }
        if (iconsVisible) {
            AnimatedIconRow(
                icon = icon,
                onIconChange = onIconChange,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Spacer(Modifier.height(16.dp))
        }

    }
}