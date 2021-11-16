package com.example.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TaskSource(private val tasksRepository: TasksRepository): PagingSource<Int, Task>() {


    override fun getRefreshKey(state: PagingState<Int, Task>): Int? {
        /*return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } */
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Task> {
        val nextPageNumber = params.key ?: 0
        val taskPage = tasksRepository.getTaskAt(nextPageNumber)
        return LoadResult.Page(
                data = taskPage.taskList,
                prevKey = taskPage.prevPageNumber,
                nextKey = taskPage.nextPageNumber
            )
    }
}