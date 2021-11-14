package com.example.theoldlist.core


interface UiAdapter {
    suspend fun createUiModel(): UiModel
}

interface RootUiAdapter:UiAdapter {
    val loadingUiModel:UiModel
    override suspend fun createUiModel(): UiModel
}