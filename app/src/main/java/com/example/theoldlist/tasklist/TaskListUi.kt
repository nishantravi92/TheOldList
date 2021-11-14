package com.example.theoldlist.tasklist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.task.TaskUiComposer
import com.example.theoldlist.task.TaskUiModel

class TaskListUiComposer {
    val taskUiComposer =  TaskUiComposer()

    @ExperimentalMaterialApi
    @Composable
    fun compose(taskListUiModel: TaskListUiModel, modifier: Modifier = Modifier) {
        TaskListUi(taskListUiModel, modifier, taskUiComposer)
    }
}

@ExperimentalMaterialApi
@Composable
private fun TaskListUi(taskListUiModel: TaskListUiModel, modifier: Modifier = Modifier, taskUiComposer: TaskUiComposer) {
    ReactiveUi(uiModel = taskListUiModel) { content ->

        val lazyTaskItems = content.taskUiModelFlow.collectAsLazyPagingItems()
        LazyColumn{
            items(lazyTaskItems.itemCount) { index ->
                lazyTaskItems[index]?.let { when(it) {
                    is TaskUiModel -> taskUiComposer.compose(uiModel = it, modifier)
                } }

            }
        }
    }
}

@Composable
private fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}