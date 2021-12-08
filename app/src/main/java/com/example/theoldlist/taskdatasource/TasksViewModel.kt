package com.example.theoldlist.taskdatasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.theoldlist.homelistsdatasource.EntryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.sql.Date

class TasksViewModel(tasksDao: TasksDao) : ViewModel() {

    private val tasksRepository = TasksRepository(tasksDao, viewModelScope)

    fun getAllTasks(): Flow<PagingData<Task>> {
        return tasksRepository.getAllTasks()
    }

    fun getTasksByDate(date: Date): Flow<PagingData<Task>> {
        return tasksRepository.getTasksByDate(date)
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

    fun getTask(id: String): Task {
        return tasksRepository.getTask(id)
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

    fun getTasksCountByDate(date: Date): Int {
        return tasksRepository.getTasksCountByDate(date)
    }
}
