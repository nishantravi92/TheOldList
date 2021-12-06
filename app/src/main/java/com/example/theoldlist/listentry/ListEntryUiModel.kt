package com.example.theoldlist.homepage

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.Identifyable
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel

fun interface ListEntryUiAction {
    fun onEntryClicked()
}


@Immutable
class ListEntryUiModel(
    identity: String,
    content: ListEntryUiModelContent,
) : ReactiveUiModel<ListEntryUiModelContent>, Identifyable {
    override val identity:String = identity
    override val content = mutableStateOf(content)
}

class ListEntryUiModelContent(val title: String,
                              val listEntryType: ListEntryType,
                              val numberOfTasksRemaining: Int? = null,
                              val listEntryUiAction: ListEntryUiAction)

enum class ListEntryType {
    HOME,
    STARRED,
    DUE_TODAY,
    DUE_THIS_WEEK
}