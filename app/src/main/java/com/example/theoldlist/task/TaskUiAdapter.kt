package com.example.theoldlist.task

import androidx.navigation.NavController
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.taskpage.TasksFragmentDirections

class TaskUiAdapter(
    private val tasksViewModel: TasksViewModel,
    private val navController: NavController
) {

    fun createAndSetupUiModel(task: Task): TaskUiModel {
        val taskUiModelAction = object : TaskUiModelAction {

            override fun onChecked() {
                tasksViewModel.markTaskAsCompleted(task)
            }

            override fun onLongClicked() {
                navController.navigate(TasksFragmentDirections.actionTasksFragmentToEditTaskBottomSheetFragment())
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