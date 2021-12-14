package com.example.theoldlist.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.theoldlist.core.LoadingIndicator
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class HomePageUiModelMapper : UiModelMapper {

    private val homepageUiComposer = HomePageUiComposer()

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is HomePageUiModel -> homepageUiComposer.compose(uiModel = model)
            is TransitionalUiModel -> LoadingIndicator()
            else -> throw IllegalArgumentException()
        }
    }
}
