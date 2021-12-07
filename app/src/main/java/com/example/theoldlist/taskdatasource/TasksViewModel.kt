package com.example.theoldlist.taskdatasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.theoldlist.homelistsdatasource.EntryType
import kotlinx.coroutines.flow.Flow

class TasksViewModel(tasksDao: TasksDao) : ViewModel() {

    private val tasksRepository = TasksRepository(tasksDao, viewModelScope)

    fun getAllTasks(): Flow<PagingData<Task>> {
        return tasksRepository.getAllTasks()
    }

    fun getAllStarredTasks(): Flow<PagingData<Task>> {
        return tasksRepository.getAllStarredTasks()
    }

    fun getAllTasksByEntryType(entryType: EntryType): Flow<PagingData<Task>> {
        return tasksRepository.getTasksByEntryType(entryType)
    }

    fun markTaskAsCompleted(task: Task) {
        tasksRepository.markTaskAsCompleted(task)
    }

    fun addTask(task: Task) {
        tasksRepository.addTask(task)
    }

    fun getTasksCount(): Int {
        return tasksRepository.getTasksCount()
    }

    fun getTasksCountByEntryType(entryType: EntryType): Int {
        return tasksRepository.getTasksCountByEntryType(entryType)
    }

    fun getTasksStarredCount(): Int {
        return tasksRepository.getTasksStarredCount()
    }

    fun getTasksDueTodayCount(): Int {
        return tasksRepository.getTasksCount()
    }

    fun getTasksDueThisWeekCount(): Int {
        return tasksRepository.getTasksCount()
    }
}
