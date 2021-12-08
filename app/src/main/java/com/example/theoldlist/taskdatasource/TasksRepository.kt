package com.example.theoldlist.taskdatasource

import androidx.paging.*
import com.example.theoldlist.homelistsdatasource.EntryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.sql.Date

class TasksRepository(private val tasksDao: TasksDao, private val scope: CoroutineScope) {

    private val PAGE_SIZE = 15

    fun getAllTasks(): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getAllTasks()
        }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun getTasksByDate(date: Date): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getAllTasks()
        }.flow.map { pagingData ->
            pagingData.filter { taskData ->
                taskData.dueDate != null && (date > taskData.dueDate || date.toString() == taskData.dueDate.toString())
            }
        }.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun getAllStarredTasks(): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getAllStarredTasks()
        }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun getTasksByEntryType(entryType: EntryType): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getTasksByEntryType(entryType)
        }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun markTaskAsCompleted(task: Task) {
        scope.launch {
            tasksDao.deleteTask(task)
        }
    }

    fun addTask(task: Task) {
        scope.launch {
            tasksDao.addTask(task)
        }
    }

    fun getTask(id: String): Task {
        return tasksDao.getTask(id)
    }

    fun getTasksCountByDate(date: Date): Int {
        return tasksDao.getTasksCountByDate(date)
    }

    fun getTasksCount(): Int {
        return tasksDao.getTasksCount()
    }

    fun getTasksStarredCount(): Int {
        return tasksDao.getTasksStarredCount()
    }

    fun getTasksCountByEntryType(entryType: EntryType): Int {
        return tasksDao.getTasksCountByEntryTypeCount(entryType)
    }
}
