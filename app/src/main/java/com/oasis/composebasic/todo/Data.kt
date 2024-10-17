package com.oasis.composebasic.todo

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

enum class TodoIcon(val imageVector: ImageVector, val contentDescription: String) {
    Square(Icons.Default.Email, "Expand"),
    Done(Icons.Default.Call, "Done"),
    Event(Icons.Default.Add, "Event"),
    Trash(Icons.Default.FavoriteBorder, "Restore");

    companion object {
        val Default = Square
    }
}

data class TodoItem(
    val task: String,
    val icon: TodoIcon = TodoIcon.Default,
    val id: UUID = UUID.randomUUID()
)