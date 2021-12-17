package com.example.theoldlist.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.example.theoldlist.appbar.AppBarUiComposer
import com.example.theoldlist.settings.wallpapersetting.WallpaperSettingsUiComposer
import com.example.theoldlist.ui.theme.PlainBackgroundMaterialTheme

class SettingsPageUiComposer {

    private val appbarUiComposer = AppBarUiComposer()
    private val wallpaperSettingsUiComposer = WallpaperSettingsUiComposer()

    @ExperimentalComposeUiApi
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

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun SettingsPageUi(
    uiModel: SettingsPageUiModel,
    modifier: Modifier,
    appBarUiComposer: AppBarUiComposer,
    wallpaperSettingsUiComposer: WallpaperSettingsUiComposer
) {
    PlainBackgroundMaterialTheme {
        Column {
            appBarUiComposer.compose(uiModel = uiModel.appBarUiModel, modifier)
            wallpaperSettingsUiComposer.compose(uiModel = uiModel.wallpaperSettingsUiModel)
        }
    }
}