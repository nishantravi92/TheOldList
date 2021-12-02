package com.example.theoldlist.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

class ListEntryUiComposer() {

    @Composable
    fun compose(uiModel: ListEntryUiModel, modifier: Modifier = Modifier) {
        ListEntryUi(uiModel, modifier)
    }
}

@Composable
private fun ListEntryUi(uiModel: ListEntryUiModel, modifier: Modifier) {
    val backgroundColor = Color(0x80FFFFFF)
    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(backgroundColor)
            .clickable { uiModel.listEntryUiAction.onEntryClicked() }) {
        Text(
            text = uiModel.title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding()
        )
        Divider(modifier = Modifier.fillMaxWidth())
    }
}