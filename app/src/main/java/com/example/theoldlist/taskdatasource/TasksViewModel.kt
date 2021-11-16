package com.example.theoldlist.taskdatasource

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import kotlinx.coroutines.flow.Flow

class TasksViewModel(tasksDao: TasksDao): ViewModel() {

    val tasksRepository = TasksRepository(tasksDao)

    fun getAllTasks(): Flow<PagingData<Task>> {
        return Pager(PagingConfig(50)) {
            TaskSource(tasksRepository) }.flow
    }

    fun markAsCompleted(task: Task) {
        tasksRepository.markTaskAsCompleted(task)
    }
}
