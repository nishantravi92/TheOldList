package com.example.theoldlist.core

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Joins the render bridge to the actual UI.
 */
class RenderBridge(
    composeView: ComposeView,
    rootUiAdapter: RootUiAdapter<out UiModel>,
    uiModelMapper: UiModelMapper,
    scope: LifecycleCoroutineScope
) {

    val uiModel = mutableStateOf(rootUiAdapter.loadingUiModel)

    init {
        composeView.setContent {
            uiModelMapper.mapUiModel(uiModel.value)
        }
        scope.launch { uiModel.value = rootUiAdapter.createAndSetupUiModel(scope) }
    }
}