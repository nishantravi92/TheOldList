package com.example.theoldlist.core

import androidx.compose.runtime.MutableState

interface UiModel

interface ReactiveUiModel<T>: UiModel {
    val content: MutableState<T>
}

object TransitionalUiModel: UiModel