package com.example.theoldlist.listentry

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.Identifiable
import com.example.theoldlist.core.ReactiveUiModel

fun interface ListEntryUiAction {
    fun onEntryClicked()
}


@Immutable
class ListEntryUiModel(
    identity: String,
    content: ListEntryUiModelContent,
) : ReactiveUiModel<ListEntryUiModelContent>, Identifiable {
    override val identity: String = identity
    override val content = mutableStateOf(content)
}

class ListEntryUiModelContent(
    val title: String,
    val listEntryType: ListEntryType,
    val numberOfTasksRemaining: Int? = null,
    val listEntryUiAction: ListEntryUiAction
)

enum class ListEntryType {
    HOME,
    STARRED,
    DUE_TODAY,
    DUE_THIS_WEEK,
    MOVIES_TO_WATCH,
    BOOKS_TO_READ
}