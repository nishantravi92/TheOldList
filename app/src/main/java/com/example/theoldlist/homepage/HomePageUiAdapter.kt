package com.example.theoldlist.homepage

import androidx.navigation.NavController
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.homelistsdatasource.HomeListViewModel
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.appbar.AppBarUiModelContent
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope

class HomePageUiAdapter(
    navController: NavController,
    homeListViewModel: HomeListViewModel,
    taskListViewModel: TasksViewModel
) : RootUiAdapter<HomePageUiModel> {

    override val loadingUiModel: UiModel = TransitionalUiModel

    private val homeListUiAdapter = HomePageListUiAdapter(navController, homeListViewModel, taskListViewModel)
    override suspend fun createAndSetupUiModel(coroutineScope: CoroutineScope): HomePageUiModel {
        return HomePageUiModel(
            HomePageUiModelContent(
                appBarUiModel = AppBarUiModel(
                    AppBarUiModelContent("Home")
                ),
                verticalScrollerUiModel = homeListUiAdapter.createAndSetupUiModel(coroutineScope)
            )
        )
    }
}