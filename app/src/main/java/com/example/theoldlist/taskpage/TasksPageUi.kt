package com.example.theoldlist.taskpage

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.tasklist.TaskListUiComposer
import com.example.theoldlist.taskpage.appbar.AppBarUiComposer
import com.example.theoldlist.ui.theme.TheOldListTheme

class TasksPageUiComposer {

    private val taskListUiComposer = TaskListUiComposer()
    private val appBarUiComposer = AppBarUiComposer()


    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: TasksPageUiModel) {
        TasksPageUi(uiModel, taskListUiComposer, appBarUiComposer)
    }
}

@ExperimentalMaterialApi
@Composable
private fun TasksPageUi(uiModel: TasksPageUiModel, taskListUiComposer: TaskListUiComposer, appBarUiComposer: AppBarUiComposer) {
    ReactiveUi(uiModel = uiModel) { content ->
        TheOldListTheme {
            Column {
                appBarUiComposer.compose(content.appBarUiModel)
                taskListUiComposer.compose(taskListUiModel = content.taskListUiModel)
            }
        }
    }
}