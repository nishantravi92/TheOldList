package com.example.theoldlist.taskpage

import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.tasklist.TaskListUiAdapter
import com.example.theoldlist.tasklist.TaskListUiModel
import com.example.theoldlist.taskpage.appbar.AppBarUiModel
import com.example.theoldlist.taskpage.appbar.AppBarUiModelContent

class TasksPageUiAdapter(val tasksViewModel: TasksViewModel): RootUiAdapter {
    override val loadingUiModel = TransitionalUiModel

    private val taskListUiAdapter = TaskListUiAdapter(tasksViewModel = tasksViewModel)
    override suspend fun createUiModel(): UiModel {
        return TasksPageUiModel(TasksPageUiModelContent(AppBarUiModel(uiModelContent = AppBarUiModelContent("Your todos")),
            // Bind the model type to adapter to avoid explicit cast
        taskListUiModel = taskListUiAdapter.createUiModel() as TaskListUiModel
        ))
    }
}