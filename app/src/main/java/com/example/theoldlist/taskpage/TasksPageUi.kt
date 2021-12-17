package com.example.theoldlist.taskpage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.example.theoldlist.addtask.AddTaskUiComposer
import com.example.theoldlist.appbar.AppBarUiComposer
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.tasklist.TaskListUiModelMapper
import com.example.theoldlist.ui.theme.TheOldListTheme
import com.example.theoldlist.verticalscroller.VerticalScrollerUiComposer

class TasksPageUiComposer {

    private val verticalScrollerUiComposer = VerticalScrollerUiComposer()
    private val taskListUiModelMapper = TaskListUiModelMapper()
    private val appBarUiComposer = AppBarUiComposer()
    private val addTaskUiComposer = AddTaskUiComposer()


    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: TasksPageUiModel) {
        TasksPageUi(
            uiModel,
            verticalScrollerUiComposer,
            appBarUiComposer,
            addTaskUiComposer,
            taskListUiModelMapper
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun TasksPageUi(
    uiModel: TasksPageUiModel,
    verticalScrollerUiComposer: VerticalScrollerUiComposer,
    appBarUiComposer: AppBarUiComposer,
    addTaskUiComposer: AddTaskUiComposer,
    taskListUiModelMapper: TaskListUiModelMapper
) {
    ReactiveUi(uiModel = uiModel) { content ->
        TheOldListTheme {
            Column {
                val listState = rememberLazyListState()
                appBarUiComposer.compose(content.appBarUiModel)
                Column {
                    addTaskUiComposer.compose(
                        uiModel = content.addTaskUiModel,
                        listState = listState
                    )
                    verticalScrollerUiComposer.compose(
                        verticalScrollerUiModel = content.verticalScrollerUiModel,
                        listState = listState,
                        contentPadding = PaddingValues(16.dp),
                        uiModelMapper = taskListUiModelMapper
                    )
                }
            }
        }
    }
}