package com.example.theoldlist.addtask

import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope

class AddTaskUiAdapter(
    private val tasksViewModel: TasksViewModel,
    private val listEntryTypeToAddTaskTo: EntryType
) : UiAdapter<AddTaskUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): AddTaskUiModel {
        return AddTaskUiModel { addTaskName ->
            if (!addTaskName.isEmpty()) {
                tasksViewModel.addTask(
                    Task(
                        id = createTaskId(),
                        title = addTaskName,
                        entryType = listEntryTypeToAddTaskTo
                    )
                )
            }
        }
    }
}

private fun createTaskId(): String {
    val tsLong = System.currentTimeMillis()
    return tsLong.toString()
}