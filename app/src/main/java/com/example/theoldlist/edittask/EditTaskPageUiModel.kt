package com.example.theoldlist.edittask

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel

class EditTaskPageUiModel(content: EditTaskPageUiModelContent) :
    ReactiveUiModel<EditTaskPageUiModelContent> {
    override val content = mutableStateOf(content)
}

@Stable
class EditTaskPageUiModelContent()