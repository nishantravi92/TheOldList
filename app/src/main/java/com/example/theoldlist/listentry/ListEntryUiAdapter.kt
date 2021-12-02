package com.example.theoldlist.homepage

import androidx.navigation.NavController
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.homelistsdatasource.HomeListEntry
import kotlinx.coroutines.CoroutineScope

class ListEntryUiAdapter(
    private val navController: NavController,
    private val homeListEntry: HomeListEntry
) : UiAdapter<ListEntryUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): ListEntryUiModel {
        return ListEntryUiModel(
            identity = homeListEntry.id,
            title = homeListEntry.title,
            listEntryType = convert(homeListEntry.entryType),
            listEntryUiAction = {
                val homeFragmentDirectionsToTasksFragment =
                    HomeFragmentDirections.actionHomeFragmentToTasksFragment()
                navController.navigate(homeFragmentDirectionsToTasksFragment)
            })
    }

    private fun convert(entryType: EntryType): ListEntryType {
        return if (entryType == EntryType.HOME) ListEntryType.HOME
        else if (entryType == EntryType.STARRED) ListEntryType.STARRED
        else ListEntryType.HOME
    }
}