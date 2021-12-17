package com.example.theoldlist.addtask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.theoldlist.R

class AddTaskUiComposer() {

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: AddTaskUiModel, modifier: Modifier = Modifier, listState: LazyListState) {
        AddTaskUi(uiModel, modifier, listState)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun AddTaskUi(uiModel: AddTaskUiModel, modifier: Modifier, listState: LazyListState) {
    Row(
        modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        var addTaskName by rememberSaveable { mutableStateOf("") }
        val backgroundColor = Color(0x12FFFFFF)
        val focusManager = LocalFocusManager.current
        TextField(
            value = addTaskName,
            label = { Text(text = "Add task") },
            textStyle = MaterialTheme.typography.subtitle1,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            keyboardActions = KeyboardActions(onDone = {
                addTaskName =
                    sendAddTaskButtonCLickEventAndReset(uiModel.addTaskUiAction, addTaskName)
            },
                onPrevious = {
                    sendAddTaskButtonCLickEventAndReset(
                        uiModel.addTaskUiAction,
                        addTaskName
                    )
                }),
            leadingIcon = {
                Icon(
                    Icons.Filled.Add,
                    "Add task icon",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            addTaskName = sendAddTaskButtonCLickEventAndReset(
                                uiModel.addTaskUiAction,
                                addTaskName
                            )
                        }
                )
            },
            onValueChange = { addTaskName = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onKeyEvent {
                    if (it.key == Key.Back) {
                        focusManager.clearFocus(false)
                    }
                    false
                },
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = colorResource(id = R.color.grey_200),
                backgroundColor = backgroundColor,
                cursorColor = colorResource(id = R.color.grey_50),
                textColor = colorResource(id = R.color.grey_50),
                focusedIndicatorColor = colorResource(id = R.color.grey_200),
                leadingIconColor = colorResource(id = R.color.grey_50),
                focusedLabelColor = colorResource(id = R.color.grey_200)
            )
        )
    }
}

private fun sendAddTaskButtonCLickEventAndReset(
    addTaskUiAction: AddTaskUiAction,
    taskName: String
): String {
    if (taskName.isNotEmpty()) {
        addTaskUiAction.onAddTaskButtonClick(taskName)
    }
    return ""
}
