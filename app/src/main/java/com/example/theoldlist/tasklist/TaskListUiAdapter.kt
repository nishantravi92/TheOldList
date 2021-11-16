package com.example.theoldlist.tasklist

import androidx.paging.map
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.task.TaskUiAdapter
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map

class TaskListUiAdapter(private val tasksViewModel: TasksViewModel) : UiAdapter<TaskListUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): TaskListUiModel {
        return TaskListUiModel(
            TaskListUiModelContent(
                tasksViewModel.getAllTasks()
                    .map { pagingData -> pagingData.map { taskData -> createTaskUiModel(taskData, tasksViewModel, scope) } })
        )
    }

    private val taskListUiAdapterMap: MutableMap<String, TaskUiAdapter> = mutableMapOf()


    private suspend fun createTaskUiModel(task: Task, tasksViewModel: TasksViewModel, scope: CoroutineScope): UiModel {
        val taskUiAdapter = if(taskListUiAdapterMap[task.id] == null) {
            TaskUiAdapter(tasksViewModel, task)
        } else {
            taskListUiAdapterMap[task.id]!!
        }
        taskListUiAdapterMap[task.id] = taskUiAdapter
        return taskUiAdapter.createAndSetupUiModel(scope)
    }
}