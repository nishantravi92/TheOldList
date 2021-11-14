package com.example.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TaskSource(private val tasksRepository: TasksRepository): PagingSource<Int, TaskData>() {

    override fun getRefreshKey(state: PagingState<Int, TaskData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TaskData> {
        val nextPageNumber = params.key ?: 0
        val taskPage = tasksRepository.getTaskAt(nextPageNumber)
        return LoadResult.Page(
                data = taskPage.taskDataList,
                prevKey = null, // Only paging forward.
                nextKey = taskPage.nextPageNumber
            )
    }
}