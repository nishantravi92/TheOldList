package com.example.theoldlist.homepage

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModel

class HomePageUiModel(uiModelContent: HomePageUiModelContent) :
    ReactiveUiModel<HomePageUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}


@Immutable
class HomePageUiModelContent(
    val appBarUiModel: AppBarUiModel,
    val verticalScrollerUiModel: VerticalScrollerUiModel
)