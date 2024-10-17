package com.oasis.composebasic.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
//    private var _todoItems = MutableLiveData<List<TodoItem>>(listOf())
//    val todoItems:LiveData<List<TodoItem>> = _todoItems

//    fun addItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value!! + listOf(item)
//    }
//
//    fun removeItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value?.toMutableList().also {
//            it?.remove(item)
//        }
//    }


    var todoItems = mutableStateListOf<TodoItem>() //viewmodel称为状态容器
        private set

    // 当前正在编辑的索引位置
    private var currentEditingPosition by mutableIntStateOf(-1)
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditingPosition)


    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
        onEditDone()
    }

    fun onEditDone() {
        currentEditingPosition = -1
    }

    fun onEditItemSelected(item: TodoItem) {
        currentEditingPosition = todoItems.indexOf(item)
    }

    fun onEditItemChange(item: TodoItem) {
        todoItems[currentEditingPosition] = item
    }


}