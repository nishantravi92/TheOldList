package com.example.theoldlist.edittask

import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import kotlinx.coroutines.CoroutineScope

class EditTaskPageUiAdapter: RootUiAdapter<EditTaskPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    override suspend fun createAndSetupUiModel(coroutineScope: CoroutineScope): EditTaskPageUiModel {
        return EditTaskPageUiModel(EditTaskPageUiModelContent())
    }
}