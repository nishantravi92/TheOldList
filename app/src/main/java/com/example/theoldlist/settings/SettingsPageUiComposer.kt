package com.example.theoldlist.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.theoldlist.appbar.AppBarUiComposer
import com.example.theoldlist.settings.wallpaper.WallpaperSettingsUiComposer

class SettingsPageUiComposer {

    private val appbarUiComposer = AppBarUiComposer()
    private val wallpaperSettingsUiComposer = WallpaperSettingsUiComposer()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    fun compose(uiModel: SettingsPageUiModel, modifier: Modifier = Modifier) {
        SettingsPageUi(
            uiModel,
            modifier,
            appbarUiComposer,
            wallpaperSettingsUiComposer
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun SettingsPageUi(
    uiModel: SettingsPageUiModel,
    modifier: Modifier,
    appBarUiComposer: AppBarUiComposer,
    wallpaperSettingsUiComposer: WallpaperSettingsUiComposer
) {
    MaterialTheme {
        Column {
            appBarUiComposer.compose(uiModel = uiModel.appBarUiModel, modifier)
            wallpaperSettingsUiComposer.compose(uiModel = uiModel.wallpaperSettingsUiModel)
        }
    }
}