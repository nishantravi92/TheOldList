package com.example.theoldlist.tasklist

import androidx.paging.map
import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.core.UiModel
import com.example.theoldlist.task.TaskUiAdapter
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModel
import com.example.theoldlist.verticalscroller.VerticalScrollerUiModelContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map

class TaskListUiAdapter(private val tasksViewModel: TasksViewModel) :
    UiAdapter<VerticalScrollerUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): VerticalScrollerUiModel {
        val content = VerticalScrollerUiModelContent(
            tasksViewModel.getAllTasks()
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
            TaskUiAdapter(tasksViewModel)
        } else {
            taskListUiAdapterMap[task.id]!!
        }
        taskListUiAdapterMap[task.id] = taskUiAdapter
        return taskUiAdapter.createAndSetupUiModel(task)
    }
}