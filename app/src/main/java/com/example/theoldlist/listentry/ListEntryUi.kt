package com.example.theoldlist.listentry

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.theoldlist.R
import com.example.theoldlist.core.ReactiveUi

class ListEntryUiComposer() {

    @Composable
    fun compose(uiModel: ListEntryUiModel, modifier: Modifier = Modifier) {
        ListEntryUi(uiModel, modifier)
    }
}

@Composable
private fun ListEntryUi(uiModel: ListEntryUiModel, modifier: Modifier) {
    ReactiveUi(uiModel = uiModel) { content ->
        Column {
            Column(verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(MaterialTheme.colors.secondary)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(color = Color.White)
                    ) { content.listEntryUiAction.onEntryClicked() }) {
                Row(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 2.dp,
                        bottom = 6.dp,
                        end = 16.dp
                    )
                ) {
                    DisplayIcon(content)
                    Text(
                        text = content.title,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(0.8f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        content.numberOfTasksRemaining.let {
                            if (it == null) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colors.onPrimary,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(text = it.toString(),
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.onPrimary)
                            }
                        }
                    }
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun DisplayIcon(content: ListEntryUiModelContent) {
    val contentDescription = "Icon description"
    when (content.listEntryType) {
        ListEntryType.STARRED -> Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary
        )
        ListEntryType.HOME -> Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary

        )
        ListEntryType.DUE_TODAY -> Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = contentDescription,
            tint = Color.Red
        )
        ListEntryType.DUE_THIS_WEEK -> Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = contentDescription,
            tint = Color.Yellow
        )
        ListEntryType.MOVIES_TO_WATCH -> Icon(
            painter = painterResource(id = R.drawable.outline_theaters_24),
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary
        )
        ListEntryType.BOOKS_TO_READ -> Icon(
            painter = painterResource(id = R.drawable.outline_menu_book_24),
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}