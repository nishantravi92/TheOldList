package com.example.theoldlist.taskpage.appbar

import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel

class AppBarUiModel(uiModelContent: AppBarUiModelContent): ReactiveUiModel<AppBarUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}

class AppBarUiModelContent(val title: String)