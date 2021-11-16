package com.example.theoldlist.task

import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope

class TaskUiAdapter(private val tasksViewModel: TasksViewModel, private val task: Task): UiAdapter<TaskUiModel> {

    val taskUiModelAction = object : TaskUiModelAction {
        override fun onChecked() {
            tasksViewModel.markAsCompleted(task)
        }
    }

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): TaskUiModel {
        return TaskUiModel(TaskUiModelContent(title = task.title, taskUiModelAction = taskUiModelAction))
    }
}