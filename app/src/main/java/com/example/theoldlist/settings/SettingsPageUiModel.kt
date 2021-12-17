package com.example.theoldlist.settings

import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.settings.wallpaper.WallpaperSettingsUiModel

class SettingsPageUiModel(
    val appBarUiModel: AppBarUiModel,
    val wallpaperSettingsUiModel: WallpaperSettingsUiModel
) : UiModel {
}