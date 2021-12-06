package com.example.theoldlist.verticalscroller

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel
import kotlinx.coroutines.flow.Flow

class VerticalScrollerUiModel(scrollerUiModelContent: VerticalScrollerUiModelContent):
    ReactiveUiModel<VerticalScrollerUiModelContent> {
    override val content = mutableStateOf(scrollerUiModelContent)
}

@Immutable
class VerticalScrollerUiModelContent(val uiModelFlow: Flow<PagingData<UiModel>>)