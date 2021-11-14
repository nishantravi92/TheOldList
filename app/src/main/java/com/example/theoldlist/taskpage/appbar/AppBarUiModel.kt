package com.example.theoldlist.taskpage.appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel

class AppBarUiModel(uiModelContent: AppBarUiModelContent): ReactiveUiModel<AppBarUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}

class AppBarUiModelContent(val title: String)