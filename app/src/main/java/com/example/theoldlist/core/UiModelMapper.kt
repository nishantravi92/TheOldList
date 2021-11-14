package com.example.theoldlist.core

import androidx.compose.runtime.Composable

fun interface UiModelMapper {
    @Composable
    fun mapUiModel(model: UiModel)
}