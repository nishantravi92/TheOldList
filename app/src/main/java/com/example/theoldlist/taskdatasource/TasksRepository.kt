package com.example.theoldlist.taskdatasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.theoldlist.homelistsdatasource.EntryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TasksRepository(private val tasksDao: TasksDao, private val scope: CoroutineScope) {

    private val PAGE_SIZE = 15

    fun getAllTasks(): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getAllTasks() }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun getAllStarredTasks(): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getAllStarredTasks() }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }

    fun getTasksByEntryType(entryType: EntryType): Flow<PagingData<Task>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            tasksDao.getTasksByEntryType(entryType) }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
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
