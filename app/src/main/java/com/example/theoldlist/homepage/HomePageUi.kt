package com.example.theoldlist.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.example.theoldlist.appbar.AppBarUiComposer
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.ui.theme.TheOldListTheme
import com.example.theoldlist.verticalscroller.VerticalScrollerUiComposer

class HomePageUiComposer {

    private val verticalScrollerUiComposer = VerticalScrollerUiComposer()
    private val homePageUiModelMapper = HomePageListUiModelMapper()
    private val appBarUiComposer = AppBarUiComposer()

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun compose(uiModel: HomePageUiModel) {
        HomePageUi(
            uiModel,
            verticalScrollerUiComposer,
            appBarUiComposer,
            homePageUiModelMapper
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun HomePageUi(
    uiModel: HomePageUiModel,
    verticalScrollerUiComposer: VerticalScrollerUiComposer,
    appBarUiComposer: AppBarUiComposer,
    homePageListUiModelMapper: HomePageListUiModelMapper
) {
    ReactiveUi(uiModel = uiModel) { content ->
        TheOldListTheme {
            Column {
                appBarUiComposer.compose(content.appBarUiModel)
                Column {
                    verticalScrollerUiComposer.compose(
                        verticalScrollerUiModel = content.verticalScrollerUiModel,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        uiModelMapper = homePageListUiModelMapper
                    )
                }
            }
        }
    }
}