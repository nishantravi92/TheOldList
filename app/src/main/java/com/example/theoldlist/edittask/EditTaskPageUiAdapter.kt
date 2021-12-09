package com.example.theoldlist.edittask

import com.example.theoldlist.core.PageFragmentHost
import com.example.theoldlist.core.ReactiveUiAdapter
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.TransitionalUiModel
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class EditTaskPageUiAdapter(
    private val args: EditTaskBottomSheetFragmentArgs,
    private val tasksViewModel: TasksViewModel,
    viewModelScope: CoroutineScope,
    private val pageFragmentHost: PageFragmentHost,
    private val taskAndDateFlow: MutableStateFlow<Pair<Task?, Long?>> = MutableStateFlow(
        Pair(
            null,
            null
        )
    )
) : ReactiveUiAdapter<Pair<Task?, Long?>, EditTaskPageUiModel, EditTaskPageUiModelContent>(
    viewModelScope,
    taskAndDateFlow.asStateFlow()
),
    RootUiAdapter<EditTaskPageUiModel> {

    override val loadingUiModel = TransitionalUiModel

    private val editTaskPageUiAction = object : EditTaskPageUiAction {

        override fun onTaskDescriptionEdited(description: String) {
            taskAndDateFlow.value.first?.copy(description = description)
                ?.let { tasksViewModel.addTask(it) }
        }

        override fun onDueDateClicked() {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()
            picker.addOnPositiveButtonClickListener { selection ->
                taskAndDateFlow.value = Pair(taskAndDateFlow.value.first, selection)
                taskAndDateFlow.value.first?.let {
                    tasksViewModel.addTask(
                        it.copy(
                            dueDate = Date(
                                taskAndDateFlow.value.second!!
                            )
                        )
                    )
                }
            }
            picker.show(pageFragmentHost.fManager, picker.toString())
        }
    }

    override suspend fun createUiModel(
        dataSource: Pair<Task?, Long?>,
        scope: CoroutineScope
    ): EditTaskPageUiModel {
        scope.launch {
            withContext(Dispatchers.IO) {
                val task = tasksViewModel.getTask(args.taskId)
                taskAndDateFlow.value = Pair(task, task.dueDate?.time)
            }
        }
        return EditTaskPageUiModel(getEditTaskUiModelContent(dataSource))
    }

    override suspend fun updateUiModel(
        dataSource: Pair<Task?, Long?>,
        scope: CoroutineScope
    ): EditTaskPageUiModelContent {
        return getEditTaskUiModelContent(dataSource)
    }

    private fun getEditTaskUiModelContent(dataSource: Pair<Task?, Long?>): EditTaskPageUiModelContent {
        val dateString =
            if (dataSource.second != null) Date(dataSource.second!!).toString() else null
        return EditTaskPageUiModelContent(
            title = dataSource.first?.title,
            description = dataSource.first?.description,
            dueDate = dateString,
            editTaskPageUiAction = editTaskPageUiAction
        )
    }
}