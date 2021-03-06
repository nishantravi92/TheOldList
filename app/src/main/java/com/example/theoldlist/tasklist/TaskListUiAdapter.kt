package com.example.theoldlist.tasklist

import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.map
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.task.TaskUiAdapter
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.taskpage.TasksFragmentArgs
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModelContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.sql.Date
import java.util.*

class TaskListUiAdapter(
    private val tasksViewModel: TasksViewModel,
    private val navController: NavController,
    private val args: TasksFragmentArgs
) : UiAdapter<VerticalScrollerUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): VerticalScrollerUiModel {
        val content = VerticalScrollerUiModelContent(
            getCorrectPagingDataFlow(args)
                .map { pagingData ->
                    pagingData.map { taskData ->
                        createTaskUiModel(
                            taskData,
                            tasksViewModel
                        )
                    }
                })
        return VerticalScrollerUiModel(
            scrollerUiModelContent = content,
        )
    }

    private val taskListUiAdapterMap: MutableMap<String, TaskUiAdapter> = mutableMapOf()

    private fun createTaskUiModel(task: Task, tasksViewModel: TasksViewModel): UiModel {
        val taskUiAdapter = if (taskListUiAdapterMap[task.id] == null) {
            TaskUiAdapter(tasksViewModel, navController)
        } else {
            taskListUiAdapterMap[task.id]!!
        }
        taskListUiAdapterMap[task.id] = taskUiAdapter
        return taskUiAdapter.createAndSetupUiModel(task)
    }

    // TODO(This should really be abstracted away in the view model itself rather than there)
    private fun getCorrectPagingDataFlow(args: TasksFragmentArgs): Flow<PagingData<Task>> {
        return if (args.listEntryType == EntryType.STARRED) {
            tasksViewModel.getAllStarredTasks()
        } else if (args.listEntryType == EntryType.TODAY) {
            tasksViewModel.getTasksByDate(Date(Calendar.getInstance().time.time))
        } else if (args.listEntryType == EntryType.WEEK) {
            val calendar = Calendar.getInstance()
            val days = 7 - calendar.get(Calendar.DAY_OF_WEEK)
            calendar.add(Calendar.DATE, days)
            tasksViewModel.getTasksByDate(Date(calendar.time.time))
        } else if (args.listEntryType == EntryType.HOME) {
            tasksViewModel.getAllTasks()
        } else {
            tasksViewModel.getAllTasksByEntryType(args.listEntryType)
        }
    }
}