package com.example.theoldlist.homepage

import androidx.compose.runtime.Composable
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper
import com.example.theoldlist.listentry.ListEntryUiComposer

class HomePageListUiModelMapper : UiModelMapper {

    private val listEntryUiComposer = ListEntryUiComposer()

    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is ListEntryUiModel -> listEntryUiComposer.compose(uiModel = model)
        }
    }
}