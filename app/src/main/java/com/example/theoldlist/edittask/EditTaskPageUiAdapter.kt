package com.example.theoldlist.edittask

import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import kotlinx.coroutines.CoroutineScope

class EditTaskPageUiAdapter : RootUiAdapter<EditTaskPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    override suspend fun createAndSetupUiModel(coroutineScope: CoroutineScope): EditTaskPageUiModel {
        return EditTaskPageUiModel(EditTaskPageUiModelContent())
    }
}