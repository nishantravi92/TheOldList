package com.example.theoldlist.tasklist

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel
import kotlinx.coroutines.flow.Flow

class TaskListUiModel(taskListUiModelContent: TaskListUiModelContent): ReactiveUiModel<TaskListUiModelContent> {
    override val content = mutableStateOf(taskListUiModelContent)
}

@Immutable
class TaskListUiModelContent(val taskUiModelFlow: Flow<PagingData<UiModel>>)
