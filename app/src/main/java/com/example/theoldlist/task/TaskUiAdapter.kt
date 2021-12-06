package com.example.theoldlist.task

import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel

class TaskUiAdapter(private val tasksViewModel: TasksViewModel) {

    fun createAndSetupUiModel(task: Task): TaskUiModel {
        val taskUiModelAction = object : TaskUiModelAction {
            override fun onClicked() {
                tasksViewModel.markTaskAsCompleted(task)
            }

            override fun onStarClicked(isStarred: Boolean) {
                tasksViewModel.addTask(task.copy(starred = isStarred))
            }
        }
        return TaskUiModel(
            identity = task.id,
            taskUiModelContent = TaskUiModelContent(
                title = task.title,
                taskUiModelAction = taskUiModelAction,
                isStarred = task.starred
            )
        )
    }
}