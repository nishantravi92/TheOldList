package com.example.theoldlist.verticalscroller

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.theoldlist.core.*
import com.example.theoldlist.task.TaskUiModel

class VerticalScrollerUiComposer {

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(
        verticalScrollerUiModel: VerticalScrollerUiModel,
        contentPadding: PaddingValues = PaddingValues(),
        modifier: Modifier = Modifier,
        uiModelMapper: UiModelMapper
    ) {
        VerticalScrollerUi(verticalScrollerUiModel, contentPadding, modifier, uiModelMapper)
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun VerticalScrollerUi(
    verticalScrollerUiModel: VerticalScrollerUiModel,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    uiModelMapper: UiModelMapper
) {
    ReactiveUi(uiModel = verticalScrollerUiModel) { content ->
        val lazyPagingItems = content.uiModelFlow.collectAsLazyPagingItems()
        val listState = remember { ListState(lazyPagingItems.itemSnapshotList.items) }
        val state = rememberLazyListState()
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = state,
                contentPadding = contentPadding
            ) {
                itemsIndexed(
                    lazyPagingItems,
                    key = { _, uiModel ->  if(uiModel is Identifyable) {
                        uiModel.identity
                    } else ""
                    }) { index, uiModel ->
                    val isItemConflict = itemConflict(listState.items.getOrNull(index), uiModel)
                    if (isItemConflict) {

                    } else {

                    }
                    uiModel?.let {
                        uiModelMapper.mapUiModel(model = it)
                    }
                    SideEffect {
                        uiModel?.let {
                            listState.updateList(index, it)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun RenderNewItem(item: UiModel, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState =
        remember(item) {
            MutableTransitionState(false).apply { targetState = true }
        },
        enter = FadeInDelayedTransition,
        exit = FadeOutTransition,
    ) {
        content()
    }
}

@Composable
@ExperimentalAnimationApi
private fun RenderOldItem(item: UiModel, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState =
        remember(item) {
            MutableTransitionState(true).apply { targetState = false }
        },
        enter = FadeInDelayedTransition,
        exit = FadeOutTransition,
    ) {
        content()
    }
}

@Composable
private fun LoadingItem() {
    Column(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color(0x80FFFFFF),
            modifier =
            Modifier
                .size(50.dp)

        )
    }
}

private fun itemConflict(modelA: UiModel?, oldModel: UiModel?): Boolean {
    return modelA === oldModel
}

@ExperimentalAnimationApi
private val FadeOutTransition = fadeOut(animationSpec = tween(easing = LinearEasing))

@ExperimentalAnimationApi
private val FadeInInstantTransition = fadeIn(animationSpec = tween(easing = LinearEasing))

@ExperimentalAnimationApi
private val FadeInDelayedTransition =
    fadeIn(animationSpec = tween(easing = LinearEasing, delayMillis = 100))