package com.oasis.composebasic.todo

fun generatorRandomTodoItem(): TodoItem {
    val message = listOf(
        "lakjfdlkaj lkajdl",
        "welurio lkajdl",
        "andklfj lkajdl",
        "vnklaj lkajdl",
        "iouowr lkajdl",
        "adlfjoi lkajdl",
        "zljkljva lkajdl",
    ).random()
    val icon = TodoIcon.entries.toTypedArray().random()
    return TodoItem(task = message, icon = icon)
}