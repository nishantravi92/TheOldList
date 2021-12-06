package com.example.theoldlist.taskpage

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.addtask.AddTaskUiModel
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModel

class TasksPageUiModel(uiModelContent: TasksPageUiModelContent) :
    ReactiveUiModel<TasksPageUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}

@Immutable
class TasksPageUiModelContent(
    val appBarUiModel: AppBarUiModel, val addTaskUiModel: AddTaskUiModel,
    val verticalScrollerUiModel: VerticalScrollerUiModel
)