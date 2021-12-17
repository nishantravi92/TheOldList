package com.example.theoldlist.settings.wallpaper

import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel

class WallpaperSettingsUiModel(wallpaperSettingsUiModelContent: WallpaperSettingsUiModelContent) :
    ReactiveUiModel<WallpaperSettingsUiModelContent> {
    override val content = mutableStateOf(wallpaperSettingsUiModelContent)
}


interface WallpaperSettingsUiAction {
    fun onWallpaperChanged(wallpaperId: Int)
}

class WallpaperSettingsUiModelContent(
    val title: String,
    val wallpapers: List<Wallpaper>,
)

class Wallpaper(
    val wallpaperId: Int,
    val wallpaperResId: Int,
    val isCurrentlySelected: Boolean,
    val wallpaperSettingsUiAction: WallpaperSettingsUiAction
)