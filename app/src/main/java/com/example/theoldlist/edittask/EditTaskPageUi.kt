package com.example.theoldlist.edittask

import com.example.theoldlist.homepage.HomePageListUiModelMapper
import com.example.theoldlist.homepage.HomePageUiModel


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theoldlist.appbar.AppBarUiComposer
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.ui.theme.TheOldListTheme
import com.example.theoldlist.verticalscroller.VerticalScrollerUiComposer

class EditTaskPageUiComposer {

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: EditTaskPageUiModel, modifier: Modifier = Modifier) {
        EditTaskPageUi(
            uiModel,
            modifier
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun EditTaskPageUi(
    uiModel: EditTaskPageUiModel,
    modifier: Modifier
) {
    ReactiveUi(uiModel = uiModel) { content ->
            Column {
                Text(text = "This is some sample text for testing")
        }
    }
}
