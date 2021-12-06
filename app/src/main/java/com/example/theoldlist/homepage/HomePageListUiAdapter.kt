package com.example.theoldlist.homepage

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.map
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.homelistsdatasource.HomeListEntry
import com.example.theoldlist.homelistsdatasource.HomeListViewModel
import com.example.theoldlist.listentry.ListEntryUiAdapter
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModelContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map

class HomePageListUiAdapter(
    private val navController: NavController,
    private val homeListViewModel: HomeListViewModel,
    private val taskListViewModel: TasksViewModel,
) : UiAdapter<VerticalScrollerUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): VerticalScrollerUiModel {
        val content = VerticalScrollerUiModelContent(
            homeListViewModel.getAllEntries()
                .map { pagingData ->
                    pagingData.map { homeListEntry ->
                        createHomeListEntryUiModel(
                            homeListEntry,
                            scope
                        )
                    }
                })
        return VerticalScrollerUiModel(content)
    }

    private val homeEntryListUiAdapterMap: MutableMap<String, ListEntryUiAdapter> = mutableMapOf()


    private suspend fun createHomeListEntryUiModel(
        homeListEntry: HomeListEntry,
        scope: CoroutineScope
    ): UiModel {
        val listEntryUiAdapter = if (homeEntryListUiAdapterMap[homeListEntry.id] == null) {
            ListEntryUiAdapter(
                navController,
                homeListEntry,
                taskListViewModel,
                homeListViewModel.viewModelScope
            )
        } else {
            homeEntryListUiAdapterMap[homeListEntry.id]!!
        }
        homeEntryListUiAdapterMap[homeListEntry.id] = listEntryUiAdapter
        return listEntryUiAdapter.createAndSetupUiModel(scope)
    }
}