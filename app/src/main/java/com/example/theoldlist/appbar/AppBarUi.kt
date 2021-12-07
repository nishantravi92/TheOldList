package com.example.theoldlist.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.ReactiveUi


class AppBarUiComposer() {

    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: AppBarUiModel, modifier: Modifier = Modifier) {
        AppBarUi(uiModel, modifier)
    }
}

@Composable
private fun AppBarUi(uiModel: AppBarUiModel, modifier: Modifier) {
    ReactiveUi(uiModel = uiModel) { content ->
        TopAppBar(backgroundColor = Color.Red) {
            Row {
                content.navItemUiModel?.let {
                    when (it) {
                        is NavItemUiModel.BackButtonNavItemUiModel ->
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 2.dp)
                                    .clickable { it.backButtonNavItemAction.onBackButtonNavItemClicked() })
                    }
                }
                Text(
                    text = content.title,
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    modifier = modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}