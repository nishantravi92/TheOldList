package com.example.theoldlist.task

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.theoldlist.R
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.core.roundedShape

class TaskUiComposer() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: TaskUiModel, modifier: Modifier = Modifier) {
        TaskUi(uiModel, modifier)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun TaskUi(uiModel: TaskUiModel, modifier: Modifier) {
    val backgroundColor = Color(0x80FFFFFF)
    ReactiveUi(uiModel = uiModel) { value ->
        var shouldHideView by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .animateContentSize(animationSpec = ANIMTED_CONTENT_SIZE_SPEC)
                .padding(vertical = 1.dp)
                .getSizeModifier(shouldHideView)
                .fillMaxWidth()
                .roundedShape()
                .background(backgroundColor)
                .combinedClickable(onLongClick = { value.taskUiModelAction.onLongClicked() }) { }
        ) {
            Spacer(modifier = modifier.width(width = 8.dp))
            Checkbox(
                checked = false,
                colors = CheckboxDefaults.colors(uncheckedColor = Color.DarkGray),
                onCheckedChange = {
                    shouldHideView = true
                    value.taskUiModelAction.onChecked()
                })
            Column(modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(0.8f)) {
                Text(
                    text = value.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                value.dueDate?.let {
                    Text(
                        text = "Due: $it",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                val painterResource =
                    if (!value.isStarred) painterResource(id = R.drawable.ic_baseline_star_outline_24)
                    else painterResource(
                        id = R.drawable.ic_baseline_star_filled_24
                    )
                Icon(
                    painterResource,
                    "Star task",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(24.dp)
                        .clickable {
                            value.taskUiModelAction.onStarClicked(!value.isStarred)
                        }
                )
            }
        }
    }
}


private fun Modifier.getSizeModifier(shouldHideView: Boolean): Modifier {
    return if (!shouldHideView) {
        this
    } else this.then(Modifier.size(0.dp))
}


private val ANIMTED_CONTENT_SIZE_SPEC = tween<IntSize>(durationMillis = 4000, easing = LinearEasing)
