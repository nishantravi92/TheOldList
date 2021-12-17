package com.example.theoldlist.settings

import androidx.navigation.NavController
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.appbar.AppBarUiModelContent
import com.example.theoldlist.appbar.NavItemUiModel
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.settings.wallpapersetting.WallpaperSettingsUiAdapter
import kotlinx.coroutines.CoroutineScope

class SettingsPageRootUiAdapter(
    private val navController: NavController,
    uiModelCoroutineScope: CoroutineScope
) : RootUiAdapter<SettingsPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    private val wallpaperSettingsUiAdapter = WallpaperSettingsUiAdapter(uiModelCoroutineScope)

    override suspend fun createAndSetupUiModel(coroutineScope: CoroutineScope): SettingsPageUiModel {
        return SettingsPageUiModel(
            appBarUiModel = AppBarUiModel(
                AppBarUiModelContent("Settings",
                    navItemUiModel = NavItemUiModel.BackButtonNavItemUiModel {
                        navController.navigateUp()
                    }
                )
            ),
            wallpaperSettingsUiModel = wallpaperSettingsUiAdapter.createAndSetupUiModel(
                coroutineScope
            )
        )
    }
}