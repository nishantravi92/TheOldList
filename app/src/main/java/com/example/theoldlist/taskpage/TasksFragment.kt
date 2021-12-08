package com.example.theoldlist.taskpage

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.theoldlist.core.*
import com.example.theoldlist.core.application.TheOldListApplication
import com.example.theoldlist.taskdatasource.TasksViewModel

class TasksFragment : BaseFragment() {

    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        val tasksListViewModel: TasksViewModel by
        viewModelsFactory { TasksViewModel((activity?.application as TheOldListApplication).tasksDao) }
        val args: TasksFragmentArgs by navArgs()
        return TasksPageUiAdapter(findNavController(), tasksListViewModel, args)
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return TasksPageUiModelMapper()
    }
}