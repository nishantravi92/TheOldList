package com.example.theoldlist.tasklist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.core.UiModelMapper
import com.example.theoldlist.task.TaskUiComposer
import com.example.theoldlist.task.TaskUiModel

class TaskListUiModelMapper : UiModelMapper {
    private val taskUiComposer = TaskUiComposer()

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @Composable
    override fun mapUiModel(model: UiModel) {
        when (model) {
            is TaskUiModel -> taskUiComposer.compose(uiModel = model)
            else -> LoadingItem()
        }
    }
}

@Composable
private fun LoadingItem() {
    Column(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color(0x80FFFFFF),
            modifier =
            Modifier
                .size(50.dp)

        )
    }
}