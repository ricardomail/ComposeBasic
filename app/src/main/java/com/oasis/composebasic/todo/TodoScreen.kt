package com.oasis.composebasic.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun TodoScreen(
    item: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,
    currentEditing: TodoItem?,
    onStartEdit: (TodoItem) -> Unit,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit

) {
    Column {
        val enableTopSection = (currentEditing == null)
        if (enableTopSection) {
            TodoItemInputBackground(elevate = true) {
                TodoItemEntryInput(onAddItem)
            }
        } else {
            Text(
                text = "Editing Item",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }


        LazyColumn(modifier = Modifier.weight(1f), contentPadding = PaddingValues(top = 8.dp)) {
            items(item) {
                if (currentEditing?.id == it.id) {
                    TodoItemInlineEditor(
                        item = currentEditing,
                        onEditItemChange = onEditItemChange,
                        onEditDone = onEditDone,
                        onRemoveItem = onRemoveItem
                    )
                } else {
                    TodoRow(it, modifier = Modifier.fillParentMaxWidth(), onItemClick = onStartEdit)
                }

            }
        }

        Button(
            onClick = {
                onAddItem(generatorRandomTodoItem())
            }, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "add random item")
        }
    }
}

@Composable
fun TodoRow(item: TodoItem, modifier: Modifier = Modifier, onItemClick: (TodoItem) -> Unit) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onItemClick(item)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = item.task)
        val iconAlpha: Float = remember(item.id) { randomTint() }
        Icon(
            imageVector = item.icon.imageVector,
            contentDescription = item.icon.contentDescription,
            tint = LocalContentColor.current.copy(alpha = iconAlpha)
        )
    }
}


private fun randomTint(): Float {
    return Random.nextFloat().coerceIn(0.3f, 0.9f)
}