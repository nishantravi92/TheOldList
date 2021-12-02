package com.example.theoldlist.homepage

import androidx.compose.runtime.Immutable
import com.example.theoldlist.core.Identifyable
import com.example.theoldlist.core.UiModel

fun interface ListEntryUiAction {
    fun onEntryClicked()
}


@Immutable
class ListEntryUiModel(
    identity: String,
    val title: String,
    val listEntryType: ListEntryType,
    val numberOfTasksRemaining: Int? = null,
    val listEntryUiAction: ListEntryUiAction
) : UiModel, Identifyable {
    override val identity:String = identity
}

enum class ListEntryType {
    HOME,
    STARRED
}