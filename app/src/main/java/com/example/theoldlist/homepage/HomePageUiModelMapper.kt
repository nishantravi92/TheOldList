package com.example.theoldlist.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper
import java.lang.IllegalArgumentException

class HomePageUiModelMapper: UiModelMapper {

    private val homepageUiComposer = HomePageUiComposer()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when(model) {
            is HomePageUiModel -> homepageUiComposer.compose(uiModel = model)
            else -> throw IllegalArgumentException()
        }

    }
}