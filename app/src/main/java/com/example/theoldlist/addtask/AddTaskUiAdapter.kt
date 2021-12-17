package com.example.theoldlist.addtask

import com.example.theoldlist.core.UiAdapter
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import kotlinx.coroutines.CoroutineScope
import java.sql.Date
import java.util.*

class AddTaskUiAdapter(
    private val tasksViewModel: TasksViewModel,
    private val listEntryTypeToAddTaskTo: EntryType
) : UiAdapter<AddTaskUiModel> {

    override suspend fun createAndSetupUiModel(scope: CoroutineScope): AddTaskUiModel {
        return AddTaskUiModel { addTaskName ->
            if (addTaskName.isNotEmpty()) {
                tasksViewModel.addTask(
                    Task(
                        id = createTaskId(),
                        title = addTaskName,
                        starred = listEntryTypeToAddTaskTo == EntryType.STARRED,
                        dueDate = getDueDate(listEntryTypeToAddTaskTo),
                        entryType = listEntryTypeToAddTaskTo
                    )
                )
            }
        }
    }

    private fun getDueDate(listEntryTypeToAddTaskTo: EntryType): Date? {
        return if (listEntryTypeToAddTaskTo == EntryType.TODAY) {
            Date(Calendar.getInstance().time.time)
        } else null
    }
}

private fun createTaskId(): String {
    val tsLong = System.currentTimeMillis()
    return tsLong.toString()
}