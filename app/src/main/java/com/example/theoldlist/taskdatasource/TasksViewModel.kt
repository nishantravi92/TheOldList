package com.example.theoldlist.taskdatasource

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import kotlinx.coroutines.flow.Flow

class TasksViewModel(): ViewModel() {

    private val taskSource = TaskSource(TasksRepository())

    suspend fun getAllTasks(): Flow<PagingData<TaskData>> {
        return Pager(PagingConfig(50)) { taskSource }.flow
    }
}