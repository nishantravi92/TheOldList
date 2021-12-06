package com.example.theoldlist.homepage

import androidx.navigation.fragment.findNavController
import com.example.theoldlist.core.*
import com.example.theoldlist.core.application.TheOldListApplication
import com.example.theoldlist.homelistsdatasource.HomeListViewModel
import com.example.theoldlist.taskdatasource.TasksViewModel

class HomeFragment : BaseFragment() {

    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        val homeListEntryViewModel: HomeListViewModel by
        viewModelsFactory { HomeListViewModel((activity?.application as TheOldListApplication).homeListEntryDao) }
        val tasksListViewModel: TasksViewModel by
        viewModelsFactory { TasksViewModel((activity?.application as TheOldListApplication).tasksDao) }
        return HomePageUiAdapter(findNavController(), homeListEntryViewModel, tasksListViewModel)
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return HomePageUiModelMapper()
    }
}