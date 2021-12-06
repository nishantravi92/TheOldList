package com.example.theoldlist.taskpage

import androidx.navigation.fragment.findNavController
import com.example.theoldlist.core.*
import com.example.theoldlist.core.application.TheOldListApplication
import com.example.theoldlist.taskdatasource.TasksViewModel

// TODO Need to move this to use the base fragment.
class TasksFragment : BaseFragment() {

    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        val tasksListViewModel: TasksViewModel by
        viewModelsFactory { TasksViewModel((activity?.application as TheOldListApplication).tasksDao) }
        return TasksPageUiAdapter(findNavController(), tasksListViewModel)
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return TasksPageUiModelMapper()
    }
}