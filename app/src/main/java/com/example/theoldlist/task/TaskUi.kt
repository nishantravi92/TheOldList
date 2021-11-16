package com.example.theoldlist.task

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
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
    val backgroundColor = Color(0x80FFFFFF)
    ReactiveUi(uiModel = uiModel) { value ->
        var shouldHideView by remember { mutableStateOf(false) }
        val context = LocalDensity.current
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .animateContentSize(animationSpec = ANIMTED_CONTENT_SIZE_SPEC)
                .getSizeModifier(shouldHideView)
                .padding(vertical = 1.dp)
                .roundedShape()
                .background(backgroundColor)
                .drawWithContent {
                    drawContent()
                    drawOverlay(this, shouldHideView, with(context) { 16.dp.toPx() })
                }
        ) {
            Spacer(modifier = modifier.width(width = 16.dp))
            Text(
                text = value.title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.80f)
            )
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = false,
                    colors = CheckboxDefaults.colors(uncheckedColor = Color.DarkGray),
                    onCheckedChange = {
                        shouldHideView = true
                        value.taskUiModelAction.onChecked()
                    })
                Spacer(modifier = modifier.width(width = 16.dp))
            }
        }
    }
}


private fun Modifier.getSizeModifier(shouldHideView: Boolean): Modifier {
    return if (!shouldHideView) {
        this
    } else this.then(Modifier.size(0.dp))
}

private fun drawOverlay(drawScope: DrawScope, shouldHideView: Boolean, sidePadding: Float) {
    if (shouldHideView) {
        with(drawScope) {
            drawLine(
                color = Color.Black,
                start = Offset(sidePadding, size.height / 2),
                end = Offset(size.width - sidePadding, size.height / 2),
            )
        }
    }
}

private val ANIMTED_CONTENT_SIZE_SPEC = tween<IntSize>(durationMillis = 300, easing = LinearEasing)
