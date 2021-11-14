package com.example.theoldlist.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.core.roundedShape

class TaskUiComposer() {

    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: TaskUiModel, modifier: Modifier = Modifier) {
        TaskUi(uiModel, modifier)
    }
}

@ExperimentalMaterialApi
@Composable
private fun TaskUi(uiModel: TaskUiModel, modifier: Modifier) {
    ReactiveUi(uiModel = uiModel) { value ->
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.roundedShape()) {
            Spacer(modifier = modifier.width(width = 16.dp))
            Text(text = value.title)
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = false, onCheckedChange = {value.taskUiModelAction.onChecked()})
                Spacer(modifier = modifier.width(width = 16.dp))
            }
        }
    }
}