package com.example.theoldlist.taskpage

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class TasksPageUiModelMapper: UiModelMapper {
    private val tasksPageUiComposer =  TasksPageUiComposer()

    @ExperimentalMaterialApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is TasksPageUiModel ->  tasksPageUiComposer.compose(uiModel = model)
        }
    }
}