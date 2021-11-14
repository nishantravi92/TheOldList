package com.example.theoldlist.task

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel


interface TaskUiModelAction {
    fun onChecked()
}
class TaskUiModel(taskUiModelContent: TaskUiModelContent): ReactiveUiModel<TaskUiModelContent> {
    override val content = mutableStateOf(taskUiModelContent)
}

@Immutable
class TaskUiModelContent(val title: String, val dueDate: String? = null, val taskUiModelAction: TaskUiModelAction)
