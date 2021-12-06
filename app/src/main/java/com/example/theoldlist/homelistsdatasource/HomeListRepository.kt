package com.example.theoldlist.homelistsdatasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeListRepository(
    private val homeListEntryDao: HomeListEntryDao,
    private val scope: CoroutineScope
) {

    private val PAGE_SIZE = 15

    init {
        scope.launch {
            withContext(Dispatchers.IO) {
                if (homeListEntryDao.getAllEntries().isEmpty()) {
                    homeListEntryDao.addListEntry(
                        HomeListEntry(
                            id = createHomeListId(),
                            title = "Home",
                            entryType = EntryType.HOME
                        )
                    )
                    homeListEntryDao.addListEntry(
                        HomeListEntry(
                            id = createHomeListId(),
                            title = "Starred",
                            entryType = EntryType.STARRED
                        )
                    )
                    homeListEntryDao.addListEntry(
                        HomeListEntry(
                            id = createHomeListId(),
                            title = "Due today",
                            entryType = EntryType.TODAY
                        )
                    )
                    homeListEntryDao.addListEntry(
                        HomeListEntry(
                            id = createHomeListId(),
                            title = "Due this week",
                            entryType = EntryType.WEEK
                        )
                    )
                }
            }
        }
    }

    fun getAllHomeListEntries(): Flow<PagingData<HomeListEntry>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            homeListEntryDao.getAllEntriesAsPagingSource()
        }.flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }
}

private fun createHomeListId(): String {
    val tsLong = System.currentTimeMillis()
    return tsLong.toString()
}
