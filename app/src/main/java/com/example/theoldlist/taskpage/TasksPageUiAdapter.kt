package com.example.theoldlist.taskpage

import androidx.navigation.NavController
import com.example.theoldlist.addtask.AddTaskUiAdapter
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.tasklist.TaskListUiAdapter
import com.example.theoldlist.appbar.AppBarUiModel
import com.example.theoldlist.appbar.AppBarUiModelContent
import com.example.theoldlist.appbar.NavItemUiModel
import kotlinx.coroutines.CoroutineScope

class TasksPageUiAdapter(private val navController: NavController, tasksViewModel: TasksViewModel) :
    RootUiAdapter<TasksPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    private val taskListUiAdapter = TaskListUiAdapter(tasksViewModel = tasksViewModel)
    private val addTaskUiAdapter = AddTaskUiAdapter(tasksViewModel = tasksViewModel)
    override suspend fun createAndSetupUiModel(scope: CoroutineScope): TasksPageUiModel {
        return TasksPageUiModel(
            TasksPageUiModelContent(
                AppBarUiModel(
                    uiModelContent = AppBarUiModelContent(
                        "Your todos",
                        navItemUiModel = NavItemUiModel.BackButtonNavItemUiModel(
                            backButtonNavItemAction = { navController.navigateUp() })
                    )
                ),
                addTaskUiModel = addTaskUiAdapter.createAndSetupUiModel(scope = scope),
                verticalScrollerUiModel = taskListUiAdapter.createAndSetupUiModel(scope),
            )
        )
    }
}