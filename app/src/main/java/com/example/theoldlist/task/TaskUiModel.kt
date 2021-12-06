package com.example.theoldlist.task

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.Identifyable
import com.example.theoldlist.core.ReactiveUiModel


interface TaskUiModelAction {
    fun onClicked()

    fun onStarClicked(isStarred: Boolean)
}

class TaskUiModel(identity: String, taskUiModelContent: TaskUiModelContent) : ReactiveUiModel<TaskUiModelContent>, Identifyable {
    override val content = mutableStateOf(taskUiModelContent)
    override val identity: String = identity

}

@Immutable
class TaskUiModelContent(
    val title: String,
    val dueDate: String? = null,
    val isStarred: Boolean,
    val taskUiModelAction: TaskUiModelAction
)
