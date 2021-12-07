package com.example.theoldlist.edittask

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class EditTaskPageUiModelMapper : UiModelMapper {

    private val editTaskPageUiComposer = EditTaskPageUiComposer()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is EditTaskPageUiModel -> editTaskPageUiComposer.compose(uiModel = model)
        }
    }
}