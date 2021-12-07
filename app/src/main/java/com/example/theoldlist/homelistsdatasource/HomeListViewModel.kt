package com.example.theoldlist.homelistsdatasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class HomeListViewModel(homeListEntryDao: HomeListEntryDao) : ViewModel() {

    private val homeListRepository = HomeListRepository(homeListEntryDao, viewModelScope)

    fun getAllEntries(): Flow<PagingData<HomeListEntry>> {
        return homeListRepository.getAllHomeListEntries()
    }
}