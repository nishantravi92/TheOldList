package com.example.theoldlist.edittask


import android.widget.CalendarView
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.theoldlist.R
import com.example.theoldlist.core.ReactiveUi
import com.google.android.material.chip.Chip
import java.time.LocalDate

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
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
        ) {
            TitleOrLoadingUi(content)
            val contentDescription = content.description ?: ""
            Text(
                text = "Description: $contentDescription",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 4,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "Add due date",
                    modifier = Modifier
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