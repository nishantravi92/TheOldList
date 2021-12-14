package com.example.theoldlist.edittask


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.ReactiveUi

class EditTaskPageUiComposer {

    @ExperimentalComposeUiApi
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

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun EditTaskPageUi(
    uiModel: EditTaskPageUiModel,
    modifier: Modifier
) {
    ReactiveUi(uiModel = uiModel) { content ->
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
        ) {
            TitleOrLoadingUi(content)
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current
            var addDescription by rememberSaveable { mutableStateOf(content.description ?: "") }
            Card(
                border = BorderStroke(1.dp, Color.LightGray),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    .wrapContentSize(),
                backgroundColor = Color.White
            ) {
                TextField(
                    value = addDescription,
                    label  = { Text(text = "Add description") },
                    textStyle = MaterialTheme.typography.subtitle1,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onDone = {
                        content.editTaskPageUiAction.onTaskDescriptionEdited(addDescription)
                        keyboardController?.hide()
                    }),
                    onValueChange = { addDescription = it },
                    singleLine = true,
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .onKeyEvent {
                            if (it.key == Key.Back) {
                                focusManager.clearFocus(false)
                            }
                            false
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = Color.Black,
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.White,
                        focusedLabelColor = Color.Black
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "Add due date",
                    modifier = modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                )
                DueDateChip(content)
            }
        }
    }
}

@Composable
private fun TitleOrLoadingUi(content: EditTaskPageUiModelContent) {
    val alignment = if (content.title == null) Alignment.CenterHorizontally else Alignment.Start
    Column(
        horizontalAlignment = alignment,
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        if (content.title == null) {
            CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
        } else {
            Text(
                text = content.title,
                style = MaterialTheme.typography.h5,
                maxLines = 3,
            )
        }
    }
}

@Composable
private fun DueDateChip(content: EditTaskPageUiModelContent) {
    val dueDateText = content.dueDate ?: "Add due date"
    ChipUi(chipBorderColor = Color.Black) {
        Text(
            text = dueDateText,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 2.dp)
                .clickable { content.editTaskPageUiAction.onDueDateClicked() }
        )
    }
}

@Composable
private fun ChipUi(chipBorderColor: Color, content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.wrapContentWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = chipBorderColor
        )
    ) {
        Row(
            modifier = Modifier.defaultMinSize(48.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
