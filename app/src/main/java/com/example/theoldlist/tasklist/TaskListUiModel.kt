package com.example.theoldlist.tasklist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.UiMode
import androidx.paging.PagingData
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.task.TaskUiModel
import kotlinx.coroutines.flow.Flow

class TaskListUiModel(taskListUiModelContent: TaskListUiModelContent): ReactiveUiModel<TaskListUiModelContent> {
    override val content = mutableStateOf(taskListUiModelContent)
}

class TaskListUiModelContent(val taskUiModelFlow: Flow<PagingData<UiModel>>)
