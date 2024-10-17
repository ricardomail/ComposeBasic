package com.oasis.composebasic.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.oasis.composebasic.todo.ui.theme.ComposeBasicTheme

class TodoActivity : ComponentActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                TodoActivityScreen()
            }
        }
    }

    @Composable
    fun TodoActivityScreen() {
        TodoScreen(
            item = todoViewModel.todoItems,
            onAddItem = todoViewModel::addItem,
            onRemoveItem = todoViewModel::removeItem,
            currentEditing = todoViewModel.currentEditItem,
            onStartEdit = todoViewModel::onEditItemSelected,
            onEditItemChange = todoViewModel::onEditItemChange,
            onEditDone = todoViewModel::onEditDone
        )
    }
}


