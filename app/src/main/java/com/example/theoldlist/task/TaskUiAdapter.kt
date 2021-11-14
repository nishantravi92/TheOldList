package com.example.theoldlist.task

import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.taskdatasource.TaskData

class TaskUiAdapter(private val taskData: TaskData): UiAdapter {

    val taskUiModelAction = object : TaskUiModelAction {
        override fun onChecked() {
        }
    }
    override suspend fun createUiModel(): UiModel {
        return TaskUiModel(TaskUiModelContent(title = taskData.title, taskUiModelAction = taskUiModelAction))
    }
}