package com.example.theoldlist.appbar

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.example.theoldlist.core.ReactiveUiModel
import com.example.theoldlist.core.UiModel

class AppBarUiModel(uiModelContent: AppBarUiModelContent) : ReactiveUiModel<AppBarUiModelContent> {
    override val content = mutableStateOf(uiModelContent)
}

@Immutable
class AppBarUiModelContent(
    val title: String, val navItemUiModel: NavItemUiModel? = null,
    val appBarItemUiModel: AppBarItemUiModel? = null
)

sealed class NavItemUiModel() : UiModel {
    class BackButtonNavItemUiModel(val backButtonNavItemAction: BackButtonNavItemAction) :
        NavItemUiModel() {
        fun interface BackButtonNavItemAction {
            fun onBackButtonNavItemClicked()
        }
    }
}

sealed class AppBarItemUiModel() : UiModel {
    class SettingsItemUiModel : NavItemUiModel() {
        fun interface SettingsIconAction {
            fun onSettingsIconClicked()
        }
    }
}