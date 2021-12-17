package com.example.theoldlist.taskpage

import androidx.navigation.NavController
import com.example.theoldlist.addtask.AddTaskUiAdapter
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.appbar.AppBarUiModelContent
import com.example.theoldlist.appbar.NavItemUiModel
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.tasklist.TaskListUiAdapter
import kotlinx.coroutines.CoroutineScope

class TasksPageUiAdapter(
    private val navController: NavController,
    tasksViewModel: TasksViewModel,
    private val args: TasksFragmentArgs
) :
    RootUiAdapter<TasksPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    private val taskListUiAdapter =
        TaskListUiAdapter(tasksViewModel = tasksViewModel, navController, args)
    private val addTaskUiAdapter =
        AddTaskUiAdapter(tasksViewModel = tasksViewModel, args.listEntryType)

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): TasksPageUiModel {
        return TasksPageUiModel(
            TasksPageUiModelContent(
                AppBarUiModel(
                    uiModelContent = AppBarUiModelContent(
                        getListEntryTypeAppBarTitle(args),
                        navItemUiModel = NavItemUiModel.BackButtonNavItemUiModel(
                            backButtonNavItemAction = { navController.navigateUp() })
                    )
                ),
                addTaskUiModel = addTaskUiAdapter.createAndSetupUiModel(scope = scope),
                verticalScrollerUiModel = taskListUiAdapter.createAndSetupUiModel(scope),
            )
        )
    }

    private fun getListEntryTypeAppBarTitle(args: TasksFragmentArgs): String {
        return if (args.listEntryType == EntryType.STARRED) {
            "Starred tasks"
        } else if (args.listEntryType == EntryType.TODAY) {
            "Tasks due today"
        } else if (args.listEntryType == EntryType.WEEK) {
            "Tasks due this week"
        } else if (args.listEntryType == EntryType.MOVIES) {
            "Movies to watch"
        } else if (args.listEntryType == EntryType.BOOKS) {
            "Books to read"
        } else {
            "Your todos"
        }
    }
}