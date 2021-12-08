package com.example.theoldlist.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class HomePageUiModelMapper : UiModelMapper {

    private val homepageUiComposer = HomePageUiComposer()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is HomePageUiModel -> homepageUiComposer.compose(uiModel = model)
            is TransitionalUiModel -> CircularProgressIndicator(modifier = Modifier.size(50.dp))
            else -> throw IllegalArgumentException()
        }

    }
}