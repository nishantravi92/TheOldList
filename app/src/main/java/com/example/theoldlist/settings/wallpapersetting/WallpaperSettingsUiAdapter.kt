package com.example.theoldlist.settings.wallpapersetting

import com.example.theoldlist.core.ReactiveUiAdapter
import com.example.theoldlist.settings.PreferenceUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class WallpaperSettingsUiAdapter(
    uiModelScope: CoroutineScope,
    private val wallPaperIdStateFlow: MutableStateFlow<Int> = MutableStateFlow(
        PreferenceUtils.currentlySetWallpaperId
    )
) :
    ReactiveUiAdapter<Int, WallpaperSettingsUiModel, WallpaperSettingsUiModelContent>(
        uiModelScope,
        wallPaperIdStateFlow
    ) {

    private val wallPaperSettingsUiAction = object : WallpaperSettingsUiAction {
        override fun onWallpaperChanged(wallpaperId: Int) {
            PreferenceUtils.setWallpaper(wallpaperId)
            wallPaperIdStateFlow.value = wallpaperId
        }
    }

    override suspend fun createUiModel(
        dataSource: Int,
        scope: CoroutineScope
    ): WallpaperSettingsUiModel {
        return WallpaperSettingsUiModel(updateUiModel(dataSource, scope))
    }

    override suspend fun updateUiModel(
        dataSource: Int,
        scope: CoroutineScope
    ): WallpaperSettingsUiModelContent {
        val wallPapers = WallpaperData.wallpaperIdAndResList.map {
            Wallpaper(
                it.first,
                it.second,
                isCurrentlySelected = wallPaperIdStateFlow.value == it.first,
                wallPaperSettingsUiAction
            )
        }
        return WallpaperSettingsUiModelContent(
            title = "Current wallpaper",
            wallpapers = wallPapers
        )
    }
}