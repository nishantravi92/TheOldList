package com.example.theoldlist.taskpage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.theoldlist.core.LoadingIndicator
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class TasksPageUiModelMapper : UiModelMapper {
    private val tasksPageUiComposer = TasksPageUiComposer()

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is TasksPageUiModel -> tasksPageUiComposer.compose(uiModel = model)
            is TransitionalUiModel -> LoadingIndicator()
        }
    }
}