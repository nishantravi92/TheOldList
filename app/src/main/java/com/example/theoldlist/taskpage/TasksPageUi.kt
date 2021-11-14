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
import com.example.theoldlist.ui.theme.TheOldListTheme

class TasksPageUiComposer {

    private val taskListUiComposer = TaskListUiComposer()

    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: TasksPageUiModel) {
        TasksPageUi(uiModel, taskListUiComposer)
    }
}

@ExperimentalMaterialApi
@Composable
private fun TasksPageUi(uiModel: TasksPageUiModel, taskListUiComposer: TaskListUiComposer) {
    ReactiveUi(uiModel = uiModel) { content ->
        val appBarUiModel = content.appBarUiModel
        TheOldListTheme {
            Column {
                TopAppBar {
                    Text(text = appBarUiModel.content.value.title, color = Color.Cyan, style = MaterialTheme.typography.h6)
                }
                taskListUiComposer.compose(taskListUiModel = content.taskListUiModel)
            }
        }
    }
}