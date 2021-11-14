package com.example.theoldlist.taskpage

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.tasklist.TaskListUiModel
import com.example.theoldlist.taskpage.appbar.AppBarUiModel

class TasksPageUiModel(uiModelContent: TasksPageUiModelContent): ReactiveUiModel<TasksPageUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}

@Immutable
class TasksPageUiModelContent(val appBarUiModel: AppBarUiModel, val taskListUiModel: TaskListUiModel)