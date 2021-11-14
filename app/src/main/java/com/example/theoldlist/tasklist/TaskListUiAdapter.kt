package com.example.theoldlist.tasklist

import androidx.paging.map
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.task.TaskUiAdapter
import com.example.theoldlist.taskdatasource.TaskData
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.flow.map

class TaskListUiAdapter(private val tasksViewModel: TasksViewModel) : UiAdapter {

    private val taskListUiAdapterMap: MutableMap<String, TaskUiAdapter> = mutableMapOf()

    override suspend fun createUiModel(): UiModel {
        return TaskListUiModel(
            TaskListUiModelContent(
                tasksViewModel.getAllTasks()
                    .map { pagingData -> pagingData.map { taskData -> createTaskUiModel(taskData) } })
        )
    }

    private suspend fun createTaskUiModel(taskData: TaskData): UiModel {
        val taskUiAdapter = if(taskListUiAdapterMap[taskData.id] == null) {
            TaskUiAdapter(taskData)
        } else {
            taskListUiAdapterMap[taskData.id]!!
        }
        taskListUiAdapterMap[taskData.id] = taskUiAdapter
        return taskUiAdapter.createUiModel()
    }
}