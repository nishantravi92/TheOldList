package com.example.theoldlist.edittask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel

class EditTaskPageUiModel(content: EditTaskPageUiModelContent): ReactiveUiModel<EditTaskPageUiModelContent> {
    override val content = mutableStateOf(content)
}

@Stable
class EditTaskPageUiModelContent()