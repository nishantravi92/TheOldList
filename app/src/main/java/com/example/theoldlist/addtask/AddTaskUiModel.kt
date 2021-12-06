package com.example.theoldlist.addtask

import com.example.theoldlist.core.UiModel

fun interface AddTaskUiAction {
    fun onAddTaskButtonClick(taskName: String)
}

class AddTaskUiModel(val addTaskUiAction: AddTaskUiAction): UiModel