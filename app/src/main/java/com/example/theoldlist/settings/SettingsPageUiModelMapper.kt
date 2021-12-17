package com.example.theoldlist.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.theoldlist.core.LoadingIndicator
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class SettingsPageUiModelMapper : UiModelMapper {

    private val settingsPageUiComposer = SettingsPageUiComposer()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is TransitionalUiModel -> LoadingIndicator()
            is SettingsPageUiModel -> settingsPageUiComposer.compose(uiModel = model)
            else -> throw IllegalArgumentException("Invalid settings page ui model")
        }
    }
}