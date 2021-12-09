package com.example.theoldlist.edittask

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel

class EditTaskPageUiModel(content: EditTaskPageUiModelContent) :
    ReactiveUiModel<EditTaskPageUiModelContent> {
    override val content = mutableStateOf(content)
}


interface EditTaskPageUiAction {
    fun onDueDateClicked()

    fun onTaskDescriptionEdited(description: String)
}

@Stable
class EditTaskPageUiModelContent(
    val title: String?,
    val description: String? = null,
    val dueDate: String? = null,
    val editTaskPageUiAction: EditTaskPageUiAction
)