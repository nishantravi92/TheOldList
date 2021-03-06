package com.example.theoldlist.listentry

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.theoldlist.R
import com.example.theoldlist.core.ReactiveUiAdapter
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.homelistsdatasource.HomeListEntry
import com.example.theoldlist.homepage.HomeFragmentDirections
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.util.*

class ListEntryUiAdapter(
    private val navController: NavController,
    private val homeListEntry: HomeListEntry,
    private val taskListViewModel: TasksViewModel,
    private val viewModelScope: CoroutineScope,
    private val numberOfTasksMutableFlow: MutableStateFlow<Int?> = MutableStateFlow(null),
) : ReactiveUiAdapter<Int?, ListEntryUiModel, ListEntryUiModelContent>(
    viewModelScope,
    numberOfTasksMutableFlow.asStateFlow()
) {

    override suspend fun createUiModel(dataSource: Int?, scope: CoroutineScope): ListEntryUiModel {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (homeListEntry.entryType == EntryType.TODAY) {
                    numberOfTasksMutableFlow.value = taskListViewModel.getTasksCountByDate(
                        Date(
                            Calendar.getInstance().time.time
                        )
                    )
                } else if (homeListEntry.entryType == EntryType.STARRED) {
                    numberOfTasksMutableFlow.value = taskListViewModel.getTasksStarredCount()
                } else if (homeListEntry.entryType == EntryType.WEEK) {
                    val calendar = Calendar.getInstance()
                    val days = 7 - calendar.get(Calendar.DAY_OF_WEEK)
                    calendar.add(Calendar.DATE, days)
                    numberOfTasksMutableFlow.value =
                        taskListViewModel.getTasksCountByDate(Date(calendar.time.time))
                } else if (homeListEntry.entryType == EntryType.HOME) {
                    numberOfTasksMutableFlow.value = taskListViewModel.getTasksCount()
                } else if (homeListEntry.entryType == EntryType.BOOKS) {
                    numberOfTasksMutableFlow.value =
                        taskListViewModel.getTasksCountByEntryType(EntryType.BOOKS)
                } else if (homeListEntry.entryType == EntryType.MOVIES) {
                    numberOfTasksMutableFlow.value =
                        taskListViewModel.getTasksCountByEntryType(EntryType.MOVIES)
                } else {
                    numberOfTasksMutableFlow.value =
                        taskListViewModel.getTasksCountByEntryType(homeListEntry.entryType)
                }
            }
        }
        return ListEntryUiModel(
            identity = homeListEntry.id,
            content = getUiModelContent(dataSource)
        )
    }

    override suspend fun updateUiModel(
        dataSource: Int?,
        scope: CoroutineScope
    ): ListEntryUiModelContent {
        return getUiModelContent(dataSource)
    }

    private fun getUiModelContent(numberOfTasks: Int?): ListEntryUiModelContent {
        return ListEntryUiModelContent(title = homeListEntry.title,
            listEntryType = convert(homeListEntry.entryType),
            numberOfTasksRemaining = numberOfTasks,
            listEntryUiAction = {
                val homeFragmentDirectionsToTasksFragment =
                    HomeFragmentDirections.actionHomeFragmentToTasksFragment(homeListEntry.entryType)
                navController.navigate(homeFragmentDirectionsToTasksFragment,
                    navOptions {
                        anim {
                            enter = R.anim.slide_in_right
                            exit = R.anim.slide_out_left
                            popExit = R.anim.slide_out_right
                            popEnter = R.anim.slide_in_left
                        }
                    })
            })
    }

    private fun convert(entryType: EntryType): ListEntryType {
        return if (entryType == EntryType.HOME) ListEntryType.HOME
        else if (entryType == EntryType.STARRED) ListEntryType.STARRED
        else if (entryType == EntryType.TODAY) ListEntryType.DUE_TODAY
        else if (entryType == EntryType.WEEK) ListEntryType.DUE_THIS_WEEK
        else if (entryType == EntryType.MOVIES) ListEntryType.MOVIES_TO_WATCH
        else if (entryType == EntryType.BOOKS) ListEntryType.BOOKS_TO_READ
        else ListEntryType.HOME
    }
}