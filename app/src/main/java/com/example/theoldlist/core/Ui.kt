package com.example.theoldlist.core

import androidx.compose.runtime.Composable

@Composable
inline fun <T> ReactiveUi(uiModel: ReactiveUiModel<T>, content: @Composable (T) -> Unit) {
    content(uiModel.content.value)
}