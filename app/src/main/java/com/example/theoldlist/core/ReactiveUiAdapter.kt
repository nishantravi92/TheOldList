package com.example.theoldlist.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class ReactiveUiAdapter<DataT, UiModelT, UiModelContent>(
    private val uiModelScope: CoroutineScope,
    private val dataSource: StateFlow<DataT>,
) : UiAdapter<UiModelT> where UiModelT : ReactiveUiModel<UiModelContent> {

    private lateinit var cachedData: UiModelT
    private lateinit var mutableStateFlowChannel:MutableStateFlow<DataT>

    final override suspend fun createAndSetupUiModel(scope: CoroutineScope): UiModelT {
        val uiModel = createUiModel(dataSource.value, scope)
        uiModelScope.launch(start = CoroutineStart.UNDISPATCHED) {
            dataSource.collect { data ->
                    uiModel.content.value = updateUiModel(data, scope)
            }
            cachedData = uiModel
        }

        return uiModel
    }

    abstract suspend fun createUiModel(dataSource: DataT, scope: CoroutineScope): UiModelT

    abstract suspend fun updateUiModel(dataSource: DataT, scope: CoroutineScope): UiModelContent
}