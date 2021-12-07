package com.example.theoldlist.edittask

import com.example.theoldlist.core.BaseBottomSheetFragment
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper

class EditTaskBottomSheetFragment : BaseBottomSheetFragment() {

    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        return EditTaskPageUiAdapter()
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return EditTaskPageUiModelMapper()
    }
}