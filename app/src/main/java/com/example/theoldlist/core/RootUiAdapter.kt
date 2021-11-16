package com.example.theoldlist.core

import kotlinx.coroutines.CoroutineScope

interface UiAdapter<T:UiModel> {
    suspend fun createAndSetupUiModel(scope: CoroutineScope): T
}

interface RootUiAdapter<T: UiModel>:UiAdapter<T> {
    val loadingUiModel:UiModel
    override suspend fun createAndSetupUiModel(coroutineScope: CoroutineScope): T
}