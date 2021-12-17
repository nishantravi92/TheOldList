package com.example.theoldlist.settings.wallpapersetting

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.theoldlist.core.ReactiveUi
import com.example.theoldlist.core.roundedShape

class WallpaperSettingsUiComposer {

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    fun compose(uiModel: WallpaperSettingsUiModel, modifier: Modifier = Modifier) {
        WallpaperSettingsUi(
            uiModel,
            modifier
        )
    }
}


@Composable
private fun WallpaperSettingsUi(uiModel: WallpaperSettingsUiModel, modifier: Modifier) {
    ReactiveUi(uiModel = uiModel) { content ->
        Column(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp, top = 16.dp),
                text = content.title,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                items(content.wallpapers) { wallpaper ->
                    val width = (260 * 9 / 16).dp
                    Column(Modifier.width(width)) {
                        Image(
                            painter = painterResource(id = wallpaper.wallpaperResId),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(260.dp)
                                .aspectRatio(9 / 16f)
                                .roundedShape()
                                .clickable {
                                    wallpaper.wallpaperSettingsUiAction.onWallpaperChanged(
                                        wallpaper.wallpaperId
                                    )
                                },
                            contentDescription = "Wallpaper"
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = wallpaper.isCurrentlySelected,
                                onCheckedChange = {
                                    wallpaper.wallpaperSettingsUiAction.onWallpaperChanged(
                                        wallpaper.wallpaperId
                                    )
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.colors.primary,
                                    checkmarkColor = MaterialTheme.colors.onPrimary
                                )
                            )
                        }
                    }
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}