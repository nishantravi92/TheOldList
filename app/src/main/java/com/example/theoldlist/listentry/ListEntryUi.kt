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
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.homepage.ListEntryType
import com.example.theoldlist.homepage.ListEntryUiModel
import com.example.theoldlist.homepage.ListEntryUiModelContent

class ListEntryUiComposer() {

    @Composable
    fun compose(uiModel: ListEntryUiModel, modifier: Modifier = Modifier) {
        ListEntryUi(uiModel, modifier)
    }
}

@Composable
private fun ListEntryUi(uiModel: ListEntryUiModel, modifier: Modifier) {
    ReactiveUi(uiModel = uiModel) { content ->

        val backgroundColor = Color(0x80FFFFFF)
        Column {
            Column(verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(backgroundColor)
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
                                    color = Color.Black,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(text = it.toString())
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
            contentDescription = contentDescription
        )
        ListEntryType.HOME -> Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = contentDescription
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
    }
}